class MobileWallet(nama:String, saldo:Long, noHp:String) {
    var feeTransferBank:Long = feeTransferBank
    var noHp:String = noHp

    override fun transfer(dp:DigitalPayment, nominal:Long ){
        if(dp.saldo<0){
            println("Nominal yang Anda input tidak valid")
        }
        if(dp.saldo<nominal){
            println("Transfer gagal! Saldo Anda tidak mencukupi")
        }
    }
}