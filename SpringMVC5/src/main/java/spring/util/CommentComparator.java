package spring.util;

import java.util.Comparator;

import spring.comment.CommentVO;

public class CommentComparator implements Comparator<CommentVO>{
	
	@Override
	public int compare(CommentVO o1, CommentVO o2) {
		
		if(o1.getDate().compareTo(o2.getDate())>0) {
			return 1;
		}else if(o1.getDate().compareTo(o2.getDate())==0) {
			return 0;
		}else {
			return -1;
		}
	}
	
}
