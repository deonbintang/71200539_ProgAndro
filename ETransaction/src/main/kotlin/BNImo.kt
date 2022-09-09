class BNImo(nama:String, saldo:Long, noRekening:String) {
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