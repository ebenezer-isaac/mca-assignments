const readline = require('readline-sync')
let shift = 0
const limits = [[48,57],[65,90],[97,122]]
const shiftText = (message, shift)=>{
	result = ""
	let correction = (shift>0)?-1:1
	console.log(correction)
	for(let i = 0;i<message.length;i++){
		let ascii = message.charCodeAt(i)
		limits.some(limit=>{
			if(ascii>=limit[0]&&ascii<=limit[1]){
				ascii = ascii+shift
				while(ascii<limit[0]){
					ascii = (limit[1]+correction-(limit[0]-ascii))
				}
				while(ascii>limit[1]){
					ascii = (limit[0]+correction+(ascii-limit[1]))
				}
				result+=String.fromCharCode(ascii)
				return true
			}
		})
	}
	return result	
}
while(true){
	choice = parseInt(readline.question("Choose operation from the following:\n 1. Change Shift Key\n 2. Encrypt Message\n 3. Decrypt Message\n 4. Exit\n Your Choice : "))
	if(choice==1){
		shift = parseInt(readline.question("Enter new shift value : "))
	}else if(choice ==2){
		message = readline.question("Enter message to encrypt : ")
		result = shiftText(message,shift)
		console.log(`Encrypted Message : ${result}`)
	}else if(choice ==3){
		message = readline.question("Enter message to decrypt : ")
		result = shiftText(message,parseInt(shift*-1))
		console.log(`Decrypted Message : ${result}`)
	}else if(choice ==4){
		process.exit(0)
	}
}