package ua.sertox.evo.objects;

import java.util.Comparator;

public class EvoListComparator {
	
	private static DateComparator dateComparator;
	private static NameComparator nameComparator;
	
	public static Comparator<EvoDocument> getDateComparator(){
		if (dateComparator == null){
			dateComparator = new DateComparator();
		}
		
		return dateComparator;
	}

	
	public static Comparator<EvoDocument> getNameComparator(){
		if (nameComparator == null){
			nameComparator = new NameComparator();
		}
		
		return nameComparator;
	}
	
	
	private static class NameComparator implements Comparator<EvoDocument> {

		@Override
		public int compare(EvoDocument lhs, EvoDocument rhs) {
			return lhs.getName().compareTo(rhs.getName());
		}

	}

	private static class DateComparator implements Comparator<EvoDocument> {

		@Override
		public int compare(EvoDocument lhs, EvoDocument rhs) {
			return rhs.getCreateDate().compareTo(lhs.getCreateDate());
		}

	}

}
