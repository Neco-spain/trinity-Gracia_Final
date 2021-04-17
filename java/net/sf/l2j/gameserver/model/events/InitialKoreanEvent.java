package net.sf.l2j.gameserver.model.events;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Logger;

import net.sf.l2j.gameserver.ThreadPoolManager;
	
	
public class InitialKoreanEvent
{
	private static InitialKoreanEvent _instance = null;
	protected static final Logger _log = Logger.getLogger(InitialKoreanEvent.class.getName());
	private Calendar NextRestart;
	private SimpleDateFormat format = new SimpleDateFormat("HH:mm");
	public static InitialKoreanEvent getInstance()
	{
		if(_instance == null)
			_instance = new InitialKoreanEvent();
		return _instance;
	}
	
	public String getRestartNextTime()
	{
		if(NextRestart.getTime() != null)
			return format.format(NextRestart.getTime());
		else
			return "Erro";
	}	
	
	private InitialKoreanEvent()
	{
		
	}

	public void StartCalculationOfNextRestartTime()
	{
		_log.info("[Korean Event]: Activated");
		try
		{
			Calendar currentTime = Calendar.getInstance();
			Calendar testStartTime = null;
			long flush2 = 0,timeL = 0;
			int count = 0;
			
//			for (String timeOfDay : Config.KOREAN_EVENT_INTERVAL_BY_TIME_OF_DAY)
//			{
//				testStartTime = Calendar.getInstance();
//				testStartTime.setLenient(true);
//				String[] splitTimeOfDay = timeOfDay.split(":");
//				testStartTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(splitTimeOfDay[0]));
//				testStartTime.set(Calendar.MINUTE, Integer.parseInt(splitTimeOfDay[1]));
//				testStartTime.set(Calendar.SECOND, 00);
//				if (testStartTime.getTimeInMillis() < currentTime.getTimeInMillis())
//				{
//					testStartTime.add(Calendar.DAY_OF_MONTH, 1);
//				}
//				
//				timeL = testStartTime.getTimeInMillis() - currentTime.getTimeInMillis();
//				
//				if(count == 0)
//				{
//					flush2 = timeL;
//					NextRestart = testStartTime;
//				}
//				
//				if(timeL < flush2)
//				{
//					flush2 = timeL;
//					NextRestart = testStartTime;
//				}
//				
//				count ++;
//			}
			_log.info("[AutoRestart]: Next Restart Time: " + NextRestart.getTime().toString());
			ThreadPoolManager.getInstance().scheduleGeneral(new StartRestartTask(), flush2);
		}
		catch (Exception e)
		{
			System.out.println("[Korean: The Korean automated server presented error in load restarts period config !");
		}
	}

	class StartRestartTask implements Runnable
	{
		public void run()
		{
			Korean.getInstance().startEvent();
			InitialKoreanEvent.getInstance().StartCalculationOfNextRestartTime();
			return;
		}
	}
}