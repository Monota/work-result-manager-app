package tokyo.monota.work.result.manager.domain.resource;

import java.math.BigDecimal;
import java.util.Date;

public class WorkResource {

	private Date workDate;

	private String itemTypeName;

	private Boolean itemIsNew;

	private BigDecimal itemUnitPrice;

	private Integer itemQuantity;

	public Date getWorkDate() {
		return workDate;
	}

	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}

	public String getItemTypeName() {
		return itemTypeName;
	}

	public void setItemTypeName(String itemTypeName) {
		this.itemTypeName = itemTypeName;
	}

	public Boolean getItemIsNew() {
		return itemIsNew;
	}

	public void setItemIsNew(Boolean itemIsNew) {
		this.itemIsNew = itemIsNew;
	}

	public BigDecimal getItemUnitPrice() {
		return itemUnitPrice;
	}

	public void setItemUnitPrice(BigDecimal itemUnitPrice) {
		this.itemUnitPrice = itemUnitPrice;
	}

	public Integer getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(Integer itemQuantity) {
		this.itemQuantity = itemQuantity;
	}
}
