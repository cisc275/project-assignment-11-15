package Files;
public enum Direction {
	NORTH(0), NORTHEAST(1), EAST(2), SOUTHEAST(3), SOUTH(4), SOUTHWEST(5), WEST(6), NORTHWEST(7);
	
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
