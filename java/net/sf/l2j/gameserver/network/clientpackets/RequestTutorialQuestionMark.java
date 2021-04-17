/*
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package net.sf.l2j.gameserver.network.clientpackets;

import net.sf.l2j.Config;
import net.sf.l2j.gameserver.model.actor.instance.L2PcInstance;
import net.sf.l2j.gameserver.model.quest.QuestState;

public class RequestTutorialQuestionMark extends L2GameClientPacket
{
	//     private static Logger _log = Logger.getLogger(RequestTutorialQuestionMark.class.getName());
	int _number = 0;

	protected void readImpl()
	{
		_number = readD();
	}
	protected void runImpl()
	{
		L2PcInstance player = getClient().getActiveChar();

		if(player == null)
			return;

/*		L2ClassMasterInstance.onTutorialQuestionMark(player, _number);*/

		QuestState qs = player.getQuestState("255_Tutorial");
		if(qs != null)
			qs.getQuest().notifyEvent("QM" + _number + "",null,player);
		
		if (Config.L2JMOD_ACHIEVEMENT_SYSTEM && (_number == player.getObjectId()))
		{
			qs = player.getQuestState("Achievements");
			if(qs != null)
				qs.getQuest().notifyEvent("achievements",null,player);
		}
	}

	@Override
	public String getType()
	{
		return "[C] 87 RequestTutorialQuestionMark";
	}
}
