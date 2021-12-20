const crypto = require('crypto');
const readline = require('readline-sync')
const algorithm = 'des-ecb';

const message = readline.question("Enter message to encrypt : ")
const password = readline.question("Enter password to encrypt with : ")

const key = Buffer.from("d0e276d0144890d3", "hex");

const cipher = crypto.createCipheriv(algorithm, key, null);
let encrypted = cipher.update(message, 'utf8', 'hex');
encrypted += cipher.final('hex');
console.log("Encrypted: ", encrypted);

const decipher = crypto.createDecipheriv(algorithm, key, null);
let decrypted = decipher.update(encrypted, 'hex', 'utf8');
decrypted += decipher.final('utf8');
console.log("Decrypted: ", decrypted);

