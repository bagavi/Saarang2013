package com.utils;

/*
 *	This class is the result of the unorganized Saarang and Shaastra
 * 	It maps event to there categories and gives each event name
 * 	size of event name hash is equal to number of events
 */

import java.util.HashMap;

public class gallery_manager {


	public static final String[] EventCategoryText = { "Fun", "Quiz", "Dance",
			"Lit", "lit2", "Arts", "Workshop", "Music" };


	/*
	 * THE MAP
	 */
	public static final Integer[][] eventids = {
			{ 1, 4, 7, 11, 20, 21, 25, 26, 27, 52, 65 },
			{ 2, 8, 12, 15, 19, 28, 29 }, { 3, 30, 46 },
			{ 5, 6, 16, 17, 18, 24, 31, 47, 48 },
			{ 9, 42, 43, 44, 49, 50, 57, 65 }, { 10, 32, 51, 61, 62, 63, 64 },
			{ 23, 13, 55 },
			{ 14, 22, 38, 39, 40, 41, 45, 53, 54, 56, 58, 59, 60 } };

	public static final HashMap<Integer, String> eventNameHash = new HashMap<Integer, String>();
	static {
		eventNameHash.put(1, " Adventure Zone ");
		eventNameHash.put(2, " Buzzer Quiz ");
		eventNameHash.put(3, " Choreo Night ");
		eventNameHash.put(4, " Daily Events ");
		eventNameHash.put(5, " Crossie ");
		eventNameHash.put(6, " Creative Writing  ");
		eventNameHash.put(7, " Cluedo ");
		eventNameHash.put(8, " Daily Quiz ");
		eventNameHash.put(9, " Dramatics ");
		eventNameHash.put(10, " Fine Arts ");
		eventNameHash.put(11, " Kryptx ");
		eventNameHash.put(12, " Main Quiz ");
		eventNameHash.put(13, " LecDems ");
		eventNameHash.put(14, " Light Music ");
		eventNameHash.put(15, " Online Quiz ");
		eventNameHash.put(16, " Saarang Debate ");
		eventNameHash.put(17, " Scrabble ");
		eventNameHash.put(18, " Speaking Events ");
		eventNameHash.put(19, " SpEnt Quiz ");
		eventNameHash.put(20, " Sudoku ");
		eventNameHash.put(21, " Treasure Hunt ");
		eventNameHash.put(22, " Western Music ");
		eventNameHash.put(23, " Workshops ");
		eventNameHash.put(24, " WTGW ");
		eventNameHash.put(25, " Carnival ");
		eventNameHash.put(26, " Media Events ");
		eventNameHash.put(27, " Big Plan ");
		eventNameHash.put(28, " India Quiz ");
		eventNameHash.put(29, " AV Quiz ");
		eventNameHash.put(30, " $treet$ ");
		eventNameHash.put(31, " Spelling Bee ");
		eventNameHash.put(32, " Classical Arts ");
		eventNameHash.put(33, " Road Shows ");
		eventNameHash.put(34, " PotPourrie ");
		eventNameHash.put(35, " Cinema Scope ");
		eventNameHash.put(36, " Decibels ");
		eventNameHash.put(37, " Tarang ");
		eventNameHash.put(38, " Powerchords ");
		eventNameHash.put(39, " LM Acoustyx ");
		eventNameHash.put(40, " Alankaar ");
		eventNameHash.put(41, " Classical Music ");
		eventNameHash.put(42, " Mono Acting ");
		eventNameHash.put(43, " Street Play ");
		eventNameHash.put(44, " Mad Ads ");
		eventNameHash.put(45, " Freestyle Solo ");
		eventNameHash.put(46, " Classical Dance ");
		eventNameHash.put(47, " JAM ");
		eventNameHash.put(48, " Elocution ");
		eventNameHash.put(49, " SFM ");
		eventNameHash.put(50, " Photography ");
		eventNameHash.put(51, " Dreams On Canvas ");
		eventNameHash.put(52, " Scavenger Hunt ");
		eventNameHash.put(55, " WM Solo ");
		eventNameHash.put(53, " WM acoustyx ");
		eventNameHash.put(55, " Dance Workshop ");
		eventNameHash.put(56, " Tarang And Decibels ");
		eventNameHash.put(57, " Online Photography Contest ");
		eventNameHash.put(58, " Vocals ");
		eventNameHash.put(59, " Instrumental Percussion ");
		eventNameHash.put(60, " Instrumental NonPercussion ");
		eventNameHash.put(61, " Fine Arts ");
		eventNameHash.put(62, " Fine Arts ");
		eventNameHash.put(63, " Fine Arts ");
		eventNameHash.put(64, " Fine Arts ");
		eventNameHash.put(65, " Cinemascope ");
		eventNameHash.put(66, " Big Plan Run ");
	}

	public static final Integer[] CoordMap = {

	1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 8, 16, 17, 18, 19, 20, 21,
			22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 0, 0, 0, 0, 0, 22, 14,
			14, // 40
			31, 9, 9, 9, 3, 31, 47, 47, 26, 26, 10, 21, 0, 0, 22, 22, 3, 14, // 58
			26, 32, 32, 32, 10, 10, 10, 10, 0, 27 // 68

	};
}
