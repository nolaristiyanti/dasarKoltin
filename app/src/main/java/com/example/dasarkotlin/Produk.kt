package com.example.dasarkotlin

class Produk{

    var id: Int = 0
    var nama: String = ""
    var stok: Int = 0

    constructor(nama: String, stok: Int){
        this.nama = nama
        this.stok = stok
    }

    constructor(){
    }
}