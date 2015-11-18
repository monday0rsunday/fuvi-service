package com.broduce.fuvi.service.resource;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

import com.broduce.fuvi.service.Api;
import com.broduce.fuvi.service.MessageBuilder;

@Path("/")
public class ApiResource {

	private Api api = new Api();

	@GET
	@Produces("application/json;charset=utf-8")
	@Path("/category")
	public Object getCategories(@Context HttpServletRequest req) {
		try {
			return new MessageBuilder().dataAll(api.getCategoryList()).build();
		} catch (Exception e) {
			return new MessageBuilder()
					.error(new com.broduce.fuvi.service.model.Error(1, e
							.getMessage())).build();
		}
	}

	@GET
	@Produces("application/json;charset=utf-8")
	@Path("/category/{category_id}/item")
	public Object getItems(@Context HttpServletRequest req,
			@PathParam("category_id") int categoryId,
			@DefaultValue("-1") @QueryParam("f") long from,
			@DefaultValue("0") @QueryParam("p") int page,
			@DefaultValue("15") @QueryParam("s") int size) {
		try {
			size = Math.max(0, Math.min(30, size));
			page = Math.max(1, Math.min(100, page));
			page = page - 1;// page start from 0
			return new MessageBuilder().dataAll(
					api.getItemList(categoryId, from, page, size)).build();
		} catch (Exception e) {
			return new MessageBuilder()
					.error(new com.broduce.fuvi.service.model.Error(1, e
							.getMessage())).build();
		}
	}
}
