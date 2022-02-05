pragma solidity ^0.6.0;

contract ExpenseTracker {
    
    int private expense = 0;
    
    Transaction[] public transaction;

    struct Transaction {
        address transactionOwner;
        string transactionDescription;
        int64 amount;
    }
    
    function getBalance() public view returns(int){
        return expense;
    }   
    
    function addTransaction(string memory description , int64 amount) public {
        Transaction memory tx1 = Transaction(msg.sender,description,amount);
        transaction.push(tx1);
        expense += amount;
    }
    
    function transactionCount() public view returns (uint){
        return transaction.length;
    }
}