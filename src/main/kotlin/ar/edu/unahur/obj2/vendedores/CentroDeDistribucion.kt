package ar.edu.unahur.obj2.vendedores

class CentroDeVendedores (val ubicacion: Ciudad){
    val vendedores = mutableListOf<Vendedor>()
    fun nuevoVendedor(vendedor: Vendedor) {
        if(vendedores.contains(vendedor)){
            error(String = "Error: El vendedor ya esta en la lista.")
        }else{
            vendedores.add(vendedor)
        }
    }
    fun vendedorEstrella() = vendedores.maxBy { a -> a.puntajeCertificaciones() }
    fun puedeCubrir(ciudad: Ciudad): Boolean {
        return vendedores.any { a -> a.puedeTrabajarEn(ciudad) }
    }
    fun vendedoresGenericos() = vendedores.filter { b -> b.otrasCertificaciones() >=1 }
    fun esRobusto(): Boolean {
        return vendedores.filter{ c -> c.esFirme() }.size >=3
    }
}