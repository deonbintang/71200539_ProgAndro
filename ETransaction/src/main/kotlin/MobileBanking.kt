class MobileBanking(nama:String, saldo:Long, noRekening:String) {
    var noRekening: String = noRekening
    var checkFee: Boolean = false
    var feeAntarBank: Int = 6000

    override fun transfer(dp:DigitalPayment, nominal:Long ){
        if(dp.saldo<0){
            println("Nominal yang Anda input tidak valid")
        }
        if(dp.saldo<nominal){
            println("Transfer gagal! Saldo Anda tidak mencukupi")
        }
        if(checkFee == true){
            dp.saldo-nominal-feeAntarBank
            dp.printBuktiTransfer()
        }
    }


}