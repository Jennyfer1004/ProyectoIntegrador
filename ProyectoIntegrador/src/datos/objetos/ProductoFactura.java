package datos.objetos;

public class ProductoFactura {
    private String codigoFactura;
    private String idProductoFactura;
    private String fechaRealizada;
    private String cantidadProducto;
    private String precioPorUnidad;
    private String cedulaVendedor;
    private String cedulaCliente;

    public ProductoFactura(String codigoFactura, String idProductoFactura, String fechaRealizada, String cantidadProducto, String precioPorUnidad, String cedulaVendedor, String cedulaCliente) {
        this.codigoFactura = codigoFactura;
        this.idProductoFactura = idProductoFactura;
        this.fechaRealizada = fechaRealizada;
        this.cantidadProducto = cantidadProducto;
        this.precioPorUnidad = precioPorUnidad;
        this.cedulaVendedor = cedulaVendedor;
        this.cedulaCliente = cedulaCliente;
    }

    public String getCodigoFactura() {
        return codigoFactura;
    }

    public void setCodigoFactura(String codigoFactura) {
        this.codigoFactura = codigoFactura;
    }

    public String getIdProductoFactura() {
        return idProductoFactura;
    }

    public void setIdProductoFactura(String idProductoFactura) {
        this.idProductoFactura = idProductoFactura;
    }

    public String getFechaRealizada() {
        return fechaRealizada;
    }

    public void setFechaRealizada(String fechaRealizada) {
        this.fechaRealizada = fechaRealizada;
    }

    public String getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(String cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public String getPrecioPorUnidad() {
        return precioPorUnidad;
    }

    public void setPrecioPorUnidad(String precioPorUnidad) {
        this.precioPorUnidad = precioPorUnidad;
    }

    public String getCedulaVendedor() {
        return cedulaVendedor;
    }

    public void setCedulaVendedor(String cedulaVendedor) {
        this.cedulaVendedor = cedulaVendedor;
    }

    public String getCedulaCliente() {
        return cedulaCliente;
    }

    public void setCedulaCliente(String cedulaCliente) {
        this.cedulaCliente = cedulaCliente;
    }
}
