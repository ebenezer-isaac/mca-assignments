let p = 11
let q = 3
let n = p*q
let dn = (p-1)*(q-1)
let m = 6
//public
let e = 0
let max = 0
let d = 0
primes = [2,3,5,7,11,13,17]
if(p>q){
	max = p
}else{
	max = q
}
for(let i = 3;i<max;i++){
	if((p===i || p%i!=0) && (q===i || q%i!=0)){
		e = i
		break
	}
}
for(let i =1;i<=100;i++){
	let x = (dn*i)+1
	//console.log(x)
	if(x%e==0){
		d = x/e 
		break
	}
}
console.log("Given Values : p =",p,", q =",q,", e =",e,", m =,",m)
console.log("Calculated d (public key) = ",d)
console.log("For Verification :")
let c = Math.pow(m,e)%n
console.log("Encrypted Message : ",c)
console.log("Decrypting Message with calculated d (public key) =",d," : ",Math.pow(c,d)%n)
console.log("Since both given message and calculated message are same, the calculated public key is verified")
