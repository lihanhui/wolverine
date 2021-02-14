package io.wolverine.container.docker;

public class Ulimit {
	private String name;
    private Long soft;
    private Long hard;
	public Ulimit(String name, Long soft, Long hard) {
		super();
		this.name = name;
		this.soft = soft;
		this.hard = hard;
	}
	@Override
	public String toString() {
		return "Ulimit [name=" + name + ", soft=" + soft + ", hard=" + hard + "]";
	}
	public String getName() {
		return name;
	}
	public Long getSoft() {
		return soft;
	}
	public Long getHard() {
		return hard;
	}
}
