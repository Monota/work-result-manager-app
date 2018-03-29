package tokyo.monota.work.result.manager.domain.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import tokyo.monota.work.result.manager.domain.form.WorkItemForm;
import tokyo.monota.work.result.manager.domain.resource.MonthListResource;
import tokyo.monota.work.result.manager.domain.resource.WorkResource;
import tokyo.monota.work.result.manager.domain.service.WorkService;

@Controller
@RequestMapping("/work")
public class WorkListController {

	@Autowired
	WorkService workService;

	@ModelAttribute
	public WorkItemForm initForm() {
		return new WorkItemForm();
	}

	@ResponseBody
	@GetMapping("/item/unitprice")
	public String getUnitPrice(@RequestParam("ItemTypeName") String itemTypeName, @RequestParam("ItemIsNew") Boolean itemIsNew) {
		if (itemTypeName == null) {
			return "";
		}
		if (itemIsNew == null) {
			itemIsNew = Boolean.FALSE;
		}
		return workService.getItemUnitPrice(itemTypeName, itemIsNew);
	}

	@ResponseBody
	@GetMapping("/item/totalprice")
	public String getTotalPrice(@RequestParam(value = "month", required = false) String workMonth, Model model) {
		String currentMonth;
		if (workMonth != null && !workMonth.isEmpty()) {
			currentMonth = workMonth;
		} else {
			currentMonth = new SimpleDateFormat("yyyy/MM").format(new Date());
		}
		return workService.getTotalPrice(currentMonth);
	}

	@ResponseBody
	@GetMapping(value = "/monthlist", params = "month")
	public MonthListResource getMonthList(@RequestParam("month") String workMonth) {
		return workService.getAllWorkMonths(workMonth);
	}

	@GetMapping("/list")
	public String list(@RequestParam(value = "month", required = false) String workMonth, Model model) throws Exception {
		String currentMonth;
		if (workMonth != null && !workMonth.isEmpty()) {
			currentMonth = workMonth;
		} else {
			currentMonth = new SimpleDateFormat("yyyy/MM").format(new Date());
		}
		List<WorkResource> workResourceList = workService.getWorkItemByWorkMonth(currentMonth);
		List<String> itemTypeNames = workService.getAllItemTypeNames();
		model.addAttribute("workItems", workResourceList);
		model.addAttribute("itemTypeNames", itemTypeNames);
		model.addAttribute("currentWorkMonth", currentMonth);
		return "/work/list";
	}

	@PostMapping(value = "/list/save", params = "mode=new")
	public String create(@Valid WorkItemForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes) throws Exception {
		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("ErrorMessage", "There are input errors.");
			return "redirect:/work/list";
		}

		WorkResource resource = new WorkResource();
		Date workDate = new SimpleDateFormat("yyyy-MM-dd").parse(form.getWorkDate());
		resource.setWorkDate(workDate);
		resource.setItemTypeName(form.getItemTypeName());
		resource.setItemIsNew(form.getItemIsNew());
		resource.setItemUnitPrice(form.getItemUnitPrice());
		resource.setItemQuantity(Integer.parseInt(form.getItemQuantity()));
		workService.createWorkItem(resource);

		redirectAttributes.addFlashAttribute("Message", "Created.");

		return "redirect:/work/list";
	}

	@PostMapping(value = "/list/save", params = "mode=edit")
	public String update(@Valid WorkItemForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes) throws Exception {
		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("ErrorMessage", "There are input errors.");
			return "redirect:/work/list";
		}

		WorkResource resource = new WorkResource();
		Date workDate = new SimpleDateFormat("yyyy-MM-dd").parse(form.getWorkDate());
		resource.setWorkDate(workDate);
		resource.setItemTypeName(form.getItemTypeName());
		resource.setItemIsNew(form.getItemIsNew());
		resource.setItemUnitPrice(form.getItemUnitPrice());
		resource.setItemQuantity(Integer.parseInt(form.getItemQuantity()));
		workService.updateWorkItem(resource);

		redirectAttributes.addFlashAttribute("Message", "Updated.");

		return "redirect:/work/list";
	}

	@PostMapping(value = "/list/save", params = "mode=del")
	public String delete(@Valid WorkItemForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes) throws Exception {
		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("ErrorMessage", "There are input errors.");
			return "redirect:/work/list";
		}

		WorkResource resource = new WorkResource();
		Date workDate = new SimpleDateFormat("yyyy-MM-dd").parse(form.getWorkDate());
		resource.setWorkDate(workDate);
		resource.setItemTypeName(form.getItemTypeName());
		resource.setItemIsNew(form.getItemIsNew());
		resource.setItemUnitPrice(form.getItemUnitPrice());
		resource.setItemQuantity(Integer.parseInt(form.getItemQuantity()));
		workService.deleteWorkItem(resource);

		redirectAttributes.addFlashAttribute("Message", "Deleted.");

		return "redirect:/work/list";
	}
}
