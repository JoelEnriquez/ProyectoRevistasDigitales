/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntidadesApoyo;


import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
/**
 *
 * @author joel
 */
public class Encriptar {
    private final String key = "Revista";

    public String encriptar(String cadenaEncriptar) throws Exception {
        String encriptacion = "";
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            //Hacemos un array de bytes nuestra llave para encriptar nuestra contraseña
            byte[] llavePass = md5.digest(key.getBytes("utf-8")); //Por si vienen tildes
            byte[] llaveBytes = Arrays.copyOf(llavePass, 24);

            //Nos sirve para obtener una llave de encriptacion
            SecretKey llave = new SecretKeySpec(llaveBytes, "DESede");
            Cipher cifrado = Cipher.getInstance("DESede");
            cifrado.init(Cipher.ENCRYPT_MODE, llave);

            //Iniciamos la encriptacion de la cadena por un arreglo de bytes
            byte[] textoPlanoBytes = cadenaEncriptar.getBytes("utf-8");
            byte[] buffer = cifrado.doFinal(textoPlanoBytes);
            byte[] base64Bytes = Base64.encodeBase64(buffer);

            encriptacion = new String(base64Bytes);

        } catch (UnsupportedEncodingException | InvalidKeyException | NoSuchAlgorithmException
                | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            throw new Exception("No se pudo encriptar la contraseña");
        }
        return encriptacion;
    }
}
