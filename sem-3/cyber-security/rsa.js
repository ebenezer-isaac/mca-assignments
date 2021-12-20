const crypto = require("crypto")
const readline = require('readline-sync')

const { publicKey, privateKey } = crypto.generateKeyPairSync("rsa", {	modulusLength: 2048,})

console.log(`Generated Public Key : \n${publicKey.export({type: "pkcs1",format: "pem",})}`)
console.log(`Generated Private Key : \n${privateKey.export({type: "pkcs1",format: "pem",})}`)

const data = readline.question("Enter message to encrypt : ")

const encryptedData = crypto.publicEncrypt({
		key: publicKey,
		padding: crypto.constants.RSA_PKCS1_OAEP_PADDING,
		oaepHash: "sha256",
	},Buffer.from(data)
)

console.log("Encypted Message: ", encryptedData.toString("base64"))

const decryptedData = crypto.privateDecrypt({
		key: privateKey,
		padding: crypto.constants.RSA_PKCS1_OAEP_PADDING,
		oaepHash: "sha256",
	},encryptedData
)

console.log("\nDecrypted Message: ", decryptedData.toString())

const verifiableData = "This need to be verified"

const signature = crypto.sign("sha256", Buffer.from(verifiableData), {
	key: privateKey,
	padding: crypto.constants.RSA_PKCS1_PSS_PADDING,
})

console.log(signature.toString("base64"))

const isVerified = crypto.verify(
	"sha256",
	Buffer.from(verifiableData),
	{
		key: publicKey,
		padding: crypto.constants.RSA_PKCS1_PSS_PADDING,
	},
	signature
)

console.log("\nSignature Verification: ", isVerified)