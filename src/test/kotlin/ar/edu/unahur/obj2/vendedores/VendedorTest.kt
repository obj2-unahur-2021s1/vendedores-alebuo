package ar.edu.unahur.obj2.vendedores

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue

class VendedorTest : DescribeSpec({
  val misiones = Provincia(1300000)
  val sanIgnacio = Ciudad(misiones)

  describe("Vendedor fijo") {
    val obera = Ciudad(misiones)
    val vendedorFijo = VendedorFijo(obera)

    describe("puedeTrabajarEn") {
      it("su ciudad de origen") {
        vendedorFijo.puedeTrabajarEn(obera).shouldBeTrue()
      }
      it("otra ciudad") {
        vendedorFijo.puedeTrabajarEn(sanIgnacio).shouldBeFalse()
      }
    }
    describe("esInfluyente") {
     vendedorFijo.esInfluyente().shouldBeFalse()
    }
    describe("esVersatil") {
      vendedorFijo.esVersatil().shouldBeFalse()
    }
  }

  describe("Viajante") {
    val cordoba = Provincia(2000000)
    val villaDolores = Ciudad(cordoba)
    val viajante = Viajante(listOf(misiones))

    describe("puedeTrabajarEn") {
      it("una ciudad que pertenece a una provincia habilitada") {
        viajante.puedeTrabajarEn(sanIgnacio).shouldBeTrue()
      }
      it("una ciudad que no pertenece a una provincia habilitada") {
        viajante.puedeTrabajarEn(villaDolores).shouldBeFalse()
      }
    }
    describe("esInfluyente") {
      it("Un viajante solo puede ser influyente si las ciudades suman una población superior a 10 mill.") {
        viajante.esInfluyente().shouldBeFalse()
      }
    }
  }

  describe("ComercioCorresponsal") {
    val cordoba = Provincia(2000000)
    val villaDolores = Ciudad(cordoba)
    val unComercio = ComercioCorresponsal(listOf(villaDolores))

    describe("puedeTrabajarEn") {
      it("un Comercio Corresponsal puede Trabajar En sucursal villaDolores figura en lista") {
        unComercio.puedeTrabajarEn(villaDolores).shouldBeTrue()
      }
      it("un Comercio Corresponsal no puede Trabajar En sucursal sanIgnacio no figura en lista") {
        unComercio.puedeTrabajarEn(sanIgnacio).shouldBeFalse()
      }
    }
    describe("cantProvincias") {
      it("una cantidad de provincias donde tiene sucursales ") {
        (unComercio.cantProvincias()==1).shouldBeTrue()
      }
  }
    describe("esInfluyente") {
      it("El comercio no trabaja en 5 ciudades ni en 3 provincias.") {
        unComercio.esInfluyente().shouldBeFalse()
      }
    }
  }
})
