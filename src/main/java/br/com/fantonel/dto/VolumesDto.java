package br.com.fantonel.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VolumesDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty(value = "id")
	private int id;
	
	@JsonProperty(value = "height")
	private BigDecimal height;
	
	@JsonProperty(value = "width")
	private BigDecimal width;
	
	@JsonProperty(value = "length")
	private BigDecimal length;
	
	@JsonProperty(value = "diameter")
	private BigDecimal diameter;
	
	@JsonProperty(value = "weight")
	private BigDecimal weight;
	
	@JsonProperty(value = "format")	
	private String format;
	
	@JsonProperty(value = "created_at")	
	private String createdAt;
	
	@JsonProperty(value = "updated_at")	
	private String updatedAt;

	public VolumesDto() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getHeight() {
		return height;
	}

	public void setHeight(BigDecimal height) {
		this.height = height;
	}

	public BigDecimal getWidth() {
		return width;
	}

	public void setWidth(BigDecimal width) {
		this.width = width;
	}

	public BigDecimal getLength() {
		return length;
	}

	public void setLength(BigDecimal length) {
		this.length = length;
	}

	public BigDecimal getDiameter() {
		return diameter;
	}

	public void setDiameter(BigDecimal diameter) {
		this.diameter = diameter;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return "VolumesDto [id=" + id + ", height=" + height + ", width=" + width + ", length=" + length + ", diameter="
				+ diameter + ", weight=" + weight + ", format=" + format + ", createdAt=" + createdAt + ", updatedAt="
				+ updatedAt + "]";
	}
}