pragma solidity >=0.7.0 <0.9.0;

contract Storage {
    uint256 number;

    function store(uint256 num)public{
        number = num;
    }
    function retreive() public view returns(uint256){
        return number;
    }
}
methods:{
    saveNumber(){
        storageContract.methods.store(this.newNumber_.send({from:'0xfbc8b5683551843b32c2a62841bA8E782010a98'})
        .then((resp)=>{
            console.log(resp);
        });
    }
    storeageContract.methods.retreive().call({from:'0xfbc8b5683551843b32c2a62841bA8E782010a98'})
    .then((number)=>{
        this.numberOnBlockchain = number;
    });
}