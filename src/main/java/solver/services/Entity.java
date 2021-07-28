package solver.services;

public interface Entity {
	public int getBuildTime();
	public int getCostGold();
	public Entity getResearch();
	public int getMaxQnt();
}