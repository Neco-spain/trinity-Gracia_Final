package net.sf.l2j.gameserver.handler.admincommandhandlers;


import net.sf.l2j.gameserver.handler.IAdminCommandHandler;
import net.sf.l2j.gameserver.model.actor.instance.L2PcInstance;
import net.sf.l2j.gameserver.model.events.LastTeamStanding;

public class AdminLastTeamStanding implements IAdminCommandHandler
{
	
	private static final String[] ADMIN_COMMANDS =
	{
		"admin_lastteam"
	};
	
	public boolean useAdminCommand(String command, L2PcInstance activeChar)
	{
		
		if (command.startsWith("admin_lastteam"))
		{
			LastTeamStanding.getInstance().startEvent();
		}
		return true;
		
	}
	
	public String[] getAdminCommandList()
	{
		return ADMIN_COMMANDS;
	}
	
}