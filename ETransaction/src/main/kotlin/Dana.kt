class Dana(nama:String, saldo:Long, noHp:String) {
    danaFeeTransferBank:Long = 1000
    override fun transfer(dp:DigitalPayment, nominal:Long ){
        if(dp.saldo<0){
            println("Nominal yang Anda input tidak valid")
        }
        if(dp.saldo<nominal){
            println("Transfer gagal! Saldo Anda tidak mencukupi")
        }

    }
}