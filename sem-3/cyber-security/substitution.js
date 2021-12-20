const readline = require('readline-sync')
let shift = 0
const encrypt = (message, mapTable) =>{
	let result = ""
	for(let index = 0;index<message.length;index++){
		result+=mapTable[message[index]]
	}
	return result
}
const decrypt = (message, mapTable) =>{
	let result = ""
	for(let index = 0;index<message.length;index++){		
		let key = Object.keys(mapTable).find(key =>  mapTable[`${key}`] === `${message[index]}`)
		result+=key
	}
	return result
}
const getRandomChar = ()=>{
	var universalSet = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+-=[]{};\':\",.<>/?\\|\`~';
    let index = Math.floor(Math.random() * (Math.floor(universalSet.length-1) - Math.ceil(0) + 1)) + Math.ceil(0);
    return universalSet[index]

}
const constructMapTable = ()=>{
	let mapTable={}
	let used = []
	const ref = [[97,122],[65,90],[48,57]]
	ref.forEach(range=>{
		for(let ascii = range[0];ascii<=range[1];ascii++){
			let randomChar = getRandomChar()
			while(used.includes(randomChar)===true){
				randomChar = getRandomChar()
			}
			used.push(randomChar)
			mapTable[String.fromCharCode(ascii)]=randomChar
		}
	})	
	return mapTable
}
console.log(getRandomChar())
const mapTable = constructMapTable()
while(true){
	choice = parseInt(readline.question("Choose operation from the following:\n 1. Encrypt Message\n 2. Decrypt Message\n 3. Print Key Mapping\n 4. Exit\n Your Choice : "))
	if(choice==1){
		message = readline.question("Enter message to encrypt : ")
		result = encrypt(message,mapTable)
		console.log(`Encrypted Message : ${result}`)
	}else if(choice ==2){
		message = readline.question("Enter message to decrypt : ")
		result = decrypt(message,mapTable)
		console.log(`Decrypted Message : ${result}`)
	}else if(choice ==3){
		console.log("Key Mapping : \n", mapTable)
	}else if(choice ==4){
		process.exit(0)
	}
}