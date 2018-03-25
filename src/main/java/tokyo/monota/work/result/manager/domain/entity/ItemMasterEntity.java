package tokyo.monota.work.result.manager.domain.entity;

import java.math.BigDecimal;

public class ItemMasterEntity {

	private Boolean itemIsNew;

	private String itemTypeName;

	private BigDecimal itemUnitPrice;

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
}
