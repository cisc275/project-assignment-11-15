package Files;
public enum Direction {
	UP(0), NORTHEAST(1), RIGHT(2), SOUTHEAST(3), DOWN(4), SOUTHWEST(5), LEFT(6), NORTHWEST(7);
	
	private Integer hierarchy;

	/**
	 * Initializes the numbers after each enumeration in order to be called
	 *
	 * @author Amjed Hallak
	 * @param hierarchy - the number attached to each enumeration 
	 * 
	 * */
    private Direction(final Integer hierarchy) {
        this.hierarchy = hierarchy;
    }

    public Integer getHierarchy() {
        return hierarchy;
    }


}