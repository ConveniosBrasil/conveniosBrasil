package br.com.fapen.conveniosBrasil.forms;

import java.util.ArrayList;
import java.util.List;

import br.com.fapen.conveniosBrasil.models.ItemPedidoCompra;
import br.com.fapen.conveniosBrasil.models.PedidoCompra;

public class PedidoCompraForm {
	private PedidoCompra pedidoCompra;
	private List<ItemPedidoCompra> itemPedidoCompra = new ArrayList<ItemPedidoCompra>();
	
	public PedidoCompraForm() {
		
	}
	
	public PedidoCompraForm(PedidoCompra pedido) {
		this.pedidoCompra = pedido;
		this.itemPedidoCompra.clear();
		for (ItemPedidoCompra item : pedido.getItens()) {
			itemPedidoCompra.add(item);
		}
	}
	
	public PedidoCompra getPedidoCompra() {
		return pedidoCompra;
	}
	public void setPedidoCompra(PedidoCompra pedidoCompra) {
		this.pedidoCompra = pedidoCompra;
	}
	public List<ItemPedidoCompra> getItemPedidoCompra() {
		return itemPedidoCompra;
	}
	public void setItemPedidoCompra(List<ItemPedidoCompra> itemPedidoCompra) {
		this.itemPedidoCompra = itemPedidoCompra;
	}
	
}
