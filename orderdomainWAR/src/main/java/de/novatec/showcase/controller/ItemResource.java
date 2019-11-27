package de.novatec.showcase.controller;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import de.novatec.showcase.ejb.orders.entity.Item;
import de.novatec.showcase.ejb.orders.session.ItemSessionLocal;

@ManagedBean
@Path(value = "/item")
public class ItemResource {

	@EJB
	private ItemSessionLocal itemBean;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createItem(Item item, @Context UriInfo uriInfo) {
		String id = itemBean.createItem(item);
		JsonObjectBuilder builder = Json.createObjectBuilder();
		builder.add("id", id);
		return Response.created(uriInfo.getAbsolutePathBuilder().build()).entity(builder.build()).type(MediaType.APPLICATION_JSON_TYPE).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path(value = "count")
	public Response countItem() {
		long count = itemBean.getTotalItems();
		JsonObjectBuilder builder = Json.createObjectBuilder();
		builder.add("count", count);
		return Response.ok().entity(builder.build()).type(MediaType.APPLICATION_JSON_TYPE).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path(value = "batch_size")
	public Response getBatchSize() {
		long batchSize = itemBean.getBatchSize();
		JsonObjectBuilder builder = Json.createObjectBuilder();
		builder.add("batchSize", batchSize);
		return Response.ok().entity(builder.build()).type(MediaType.APPLICATION_JSON_TYPE).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path(value = "current_max")
	public Response getCurrentMax() {
		String current_max = itemBean.getCurrentMax();
		JsonObjectBuilder builder = Json.createObjectBuilder();
		builder.add("current_max", current_max);
		return Response.ok().entity(builder.build()).type(MediaType.APPLICATION_JSON_TYPE).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path(value = "current_min")
	public Response getCurrentMin() {
		String current_min = itemBean.getCurrentMin();
		JsonObjectBuilder builder = Json.createObjectBuilder();
		builder.add("current_min", current_min);
		return Response.ok().entity(builder.build()).type(MediaType.APPLICATION_JSON_TYPE).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path(value = "{ids}")
	public Response getItems(@PathParam("ids") String itemIds) {
		List<Item> items = itemBean.getItems(itemIds);
		return Response.ok().entity(items).type(MediaType.APPLICATION_JSON_TYPE).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path(value = "forward")
	public Response forward() {
		List<Item> items = itemBean.browseForward();
		return Response.ok().entity(items).type(MediaType.APPLICATION_JSON_TYPE).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path(value = "reverse")
	public Response reverse() {
		List<Item> items = itemBean.browseReverse();
		return Response.ok().entity(items).type(MediaType.APPLICATION_JSON_TYPE).build();
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path(value = "batch_size/{size}")
	public Response setBatchSize(@PathParam("size") int batchSize) {
		itemBean.setBatchSize(batchSize);
		JsonObjectBuilder builder = Json.createObjectBuilder();
		builder.add("batchSize", itemBean.getBatchSize());
		return Response.ok().entity(builder.build()).type(MediaType.APPLICATION_JSON_TYPE).build();
	}
}