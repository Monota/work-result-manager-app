package tokyo.monota.work.result.manager.domain.form;

import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class WorkItemForm {

	@NotEmpty
	private String workDate;

	@NotEmpty
	private String itemTypeName;

	@NotNull
	private Boolean itemIsNew;

	@NotNull
	private BigDecimal itemUnitPrice;

	@NotEmpty
	@Pattern(regexp="[\\d]{1,6}")
	private String itemQuantity;

	@NotEmpty
	private String mode;

	public String getWorkDate() {
		return workDate;
	}

	public void setWorkDate(String workDate) {
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

	public String getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(String itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}
}
