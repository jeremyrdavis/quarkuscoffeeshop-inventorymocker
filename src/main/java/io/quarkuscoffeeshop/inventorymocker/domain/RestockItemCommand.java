package io.quarkuscoffeeshop.inventorymocker.domain;

public class RestockItemCommand {

    final Item item;

    final int quantity;

    public RestockItemCommand(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RestockItemCommand{");
        sb.append("item=").append(item);
        sb.append(", quantity=").append(quantity);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RestockItemCommand that = (RestockItemCommand) o;

        if (quantity != that.quantity) return false;
        return item == that.item;
    }

    @Override
    public int hashCode() {
        int result = item != null ? item.hashCode() : 0;
        result = 31 * result + quantity;
        return result;
    }

    public Item getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }
}
