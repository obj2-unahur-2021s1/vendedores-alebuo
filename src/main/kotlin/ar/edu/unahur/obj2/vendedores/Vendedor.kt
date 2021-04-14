package ar.edu.unahur.obj2.vendedores

class Certificacion(val esDeProducto: Boolean, val puntaje: Int)

abstract class Vendedor {
  val certificaciones = mutableListOf<Certificacion>()
  abstract fun puedeTrabajarEn(ciudad: Ciudad): Boolean
  fun esVersatil() =
        certificaciones.size >= 3
          && this.certificacionesDeProducto() >= 1
          && this.otrasCertificaciones() >= 1
  fun agregarCertificacion(certificacion: Certificacion) {
        certificaciones.add(certificacion)
      }
  fun esFirme() = this.puntajeCertificaciones() >= 30
  fun certificacionesDeProducto() = certificaciones.count { it.esDeProducto }
  fun otrasCertificaciones() = certificaciones.count { !it.esDeProducto }
  fun puntajeCertificaciones() = certificaciones.sumBy { c -> c.puntaje }
  abstract fun esInfluyente(): Boolean
}

class VendedorFijo(val ciudadOrigen: Ciudad) : Vendedor() {
  override fun puedeTrabajarEn(ciudad: Ciudad): Boolean {
    return ciudad == ciudadOrigen
  }
  override fun esInfluyente(): Boolean {
      return false
  }
}

class Viajante(val provinciasHabilitadas: List<Provincia>) : Vendedor() {
  override fun puedeTrabajarEn(ciudad: Ciudad): Boolean {
    return provinciasHabilitadas.contains(ciudad.provincia)
  }
  override fun esInfluyente(): Boolean {
    return provinciasHabilitadas.sumBy { p -> p.poblacion } > 10000000
  }
}
class ComercioCorresponsal(val ciudadesConSucursal: List<Ciudad>) : Vendedor() {
  override fun puedeTrabajarEn(ciudad: Ciudad): Boolean {
    return ciudadesConSucursal.contains(ciudad)
  }
  fun cantProvincias(): Int{
    return ciudadesConSucursal.map { c -> c.provincia }.size
  }
  override fun esInfluyente(): Boolean {
    return  ciudadesConSucursal.size >=5 || this.cantProvincias() >=3
  }
}