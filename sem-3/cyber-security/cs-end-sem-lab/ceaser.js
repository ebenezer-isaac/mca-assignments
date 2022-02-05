const readline = require('readline-sync')
let shift = 0
//numbers, capitals, small
const limits = [[48,57],[65,90],[97,122]]
const shiftText = (message, shift)=>{
	result = ""
	let correction = (shift>0)?-1:1
	for(let i = 0;i<message.length;i++){
		if(message[i]===" "){
			result+=" "
			continue
		}
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
shift = 6
message = "A GOOD TONGUE IS A GOOD WEAPON"
encrypted = shiftText(message,shift)
console.log(`Encrypted Message : ${encrypted}`)
console.log(`Decrypted Message : ${shiftText(encrypted,parseInt(shift*-1))}`)