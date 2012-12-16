package com.adapters;

import java.util.HashMap;

import com.saarang.R;


public class EventImgAdapter {
	private static HashMap<Integer, Integer> DanceEventHash = new HashMap<Integer, Integer>(6);
	private static HashMap<Integer, Integer> SpeakEventHash = new HashMap<Integer, Integer>(3);
	private static HashMap<Integer, Integer> LitEventHash = new HashMap<Integer, Integer>();
	private static HashMap<Integer, Integer> MusicEventHash = new HashMap<Integer, Integer>();
	private static HashMap<Integer, Integer> QuizEventHash = new HashMap<Integer, Integer>();
	private static HashMap<Integer, Integer> ThespianEventHash = new HashMap<Integer, Integer>();
	private static HashMap<Integer, Integer> FineEventHash = new HashMap<Integer, Integer>();
	private static HashMap<Integer, Integer> LecEventHash = new HashMap<Integer, Integer>();
	private static HashMap<Integer, Integer> UnwindEventHash = new HashMap<Integer, Integer>();
	private static HashMap<Integer, Integer> OtherEventHash = new HashMap<Integer, Integer>();
	private int[][] NoOfEvents = new int[2][5];
	
	public EventImgAdapter() {
		DanceEventHash.put(0, R.drawable.dance_events1);
		DanceEventHash.put(1, R.drawable.dance_events2);
		DanceEventHash.put(2, R.drawable.dance_events3);
		DanceEventHash.put(3, R.drawable.dance_events4);
		DanceEventHash.put(4, R.drawable.dance_events5);
		DanceEventHash.put(5, R.drawable.dance_events6);
		SpeakEventHash.put(0, R.drawable.speaking_events1);
		SpeakEventHash.put(1, R.drawable.speaking_events2);
		SpeakEventHash.put(2, R.drawable.speaking_events3);
		LitEventHash.put(0, R.drawable.lit_events1);
		LitEventHash.put(1, R.drawable.lit_events2);
		LitEventHash.put(2, R.drawable.lit_events3);
		LitEventHash.put(3, R.drawable.lit_events4);
		MusicEventHash.put(0, R.drawable.music_events1);
		MusicEventHash.put(1, R.drawable.music_events2);
		MusicEventHash.put(2, R.drawable.music_events3);
		QuizEventHash.put(0, R.drawable.quizzing_events1);
		QuizEventHash.put(1, R.drawable.quizzing_events2);
		QuizEventHash.put(2, R.drawable.quizzing_events3);
		QuizEventHash.put(3, R.drawable.quizzing_events4);
		QuizEventHash.put(4, R.drawable.quizzing_events5);
		QuizEventHash.put(5, R.drawable.quizzing_events6);
		ThespianEventHash.put(0, R.drawable.thespian_events1);
		ThespianEventHash.put(1, R.drawable.thespian_events2);
		ThespianEventHash.put(2, R.drawable.thespian_events3);
		ThespianEventHash.put(3, R.drawable.thespian_events4);
		FineEventHash.put(0, R.drawable.finearts_event1);
		FineEventHash.put(1, R.drawable.finearts_event2);
		FineEventHash.put(2, R.drawable.finearts_event3);
		FineEventHash.put(3, R.drawable.finearts_event4);
		FineEventHash.put(4, R.drawable.finearts_event5);
		FineEventHash.put(5, R.drawable.finearts_event6);
		FineEventHash.put(6, R.drawable.finearts_event7);
		FineEventHash.put(7, R.drawable.finearts_event8);
		FineEventHash.put(8, R.drawable.finearts_event9);
		LecEventHash.put(0, R.drawable.lecdems_events1);
		LecEventHash.put(1, R.drawable.lecdems_events2);
		UnwindEventHash.put(0, R.drawable.unwind_events1);
		UnwindEventHash.put(1, R.drawable.unwind_events2);
		UnwindEventHash.put(2, R.drawable.unwind_events3);
		UnwindEventHash.put(3, R.drawable.unwind_events4);
		UnwindEventHash.put(4, R.drawable.unwind_events5);
		UnwindEventHash.put(5, R.drawable.unwind_events6);
		OtherEventHash.put(0, R.drawable.other_events1);
		OtherEventHash.put(1, R.drawable.other_events2);
		OtherEventHash.put(2, R.drawable.other_events3);
		OtherEventHash.put(3, R.drawable.other_events4);
		OtherEventHash.put(4, R.drawable.other_events5);
		OtherEventHash.put(5, R.drawable.other_events6);
		OtherEventHash.put(6, R.drawable.other_events7);
		
		int[][] number = {{ 6, 3, 4, 3, 6}, { 4, 8, 2, 6, 7}};
		NoOfEvents =  number;
		}
	
	public int getEvent(int p,  int evt, int id) {
		int ret;
		if(p == 0) {
			switch(evt) {
				case 0:
					ret = DanceEventHash.get(id);
					break;
				case 1:
					ret = SpeakEventHash.get(id);
					break;
				case 2:
					ret = LitEventHash.get(id);
					break;
				case 3:
					ret = MusicEventHash.get(id);
					break;
				case 4:
					ret = QuizEventHash.get(id);
					break;
				default:
					ret = R.drawable.dance_events;
					break;
			}
		} else {
				switch(evt) {
					case 1:
						ret = ThespianEventHash.get(id);
						break;
					case 2:
						ret = FineEventHash.get(id);
						break;
					case 3:
						ret = LecEventHash.get(id);
						break;
					case 4:
						ret = UnwindEventHash.get(id);
						break;
					case 5:
						ret = OtherEventHash.get(id);
						break;
					default:
						ret = R.drawable.dance_events;
						break;
				}
		}
		return ret;
	}

	public int getNo(int p, int evt) {
		//return NoOfEvents[p][evt];
		if(p == 0)
			return NoOfEvents[p][evt];
		else
			return NoOfEvents[p][evt-1];
	}
}
