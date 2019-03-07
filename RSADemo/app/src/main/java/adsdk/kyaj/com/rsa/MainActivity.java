package adsdk.kyaj.com.rsa;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import java.security.PrivateKey;
import java.security.PublicKey;

public class MainActivity extends Activity implements OnClickListener
{
	private Button btn1, btn2;// 加密，解密
	private EditText et1, et2, et3;// 需加密的内容，加密后的内容，解密后的内容

	/* 密钥内容 base64 code */
//	private static String PUCLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCfRTdcPIH10gT9f31rQuIInLwe"
//			+ "" + "7fl2dtEJ93gTmjE9c2H+kLVENWgECiJVQ5sonQNfwToMKdO0b3Olf4pgBKeLThra" + ""
//			+ "z/L3nYJYlbqjHC3jTjUnZc0luumpXGsox62+PuSGBlfb8zJO6hix4GV/vhyQVCpG" + ""
//			+ "9aYqgE7zyTRZYX9byQIDAQAB" + "";
	private static String PUCLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDAiPzZFWpEaUJ7469jKQ4sxRl/" +
			"UUqN5NUm4Uf5T29KSBJZobV9oawKCHHA2PCxJAkUpGXdaILLW2DSm6kH1WMDjRNf" +
			"KXjg62CtZe0UZi3dgvw+qf+Vq7gdHXHhGtgib5urD0L44CHKK37iG8FnJ5phGxFU" +
			"knwLEgxBMnqWL7IcrQIDAQAB" + "";

//	private static String PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJ9FN1w8gfXSBP1/"
//			+ "" + "fWtC4gicvB7t+XZ20Qn3eBOaMT1zYf6QtUQ1aAQKIlVDmyidA1/BOgwp07Rvc6V/" + ""
//			+ "imAEp4tOGtrP8vedgliVuqMcLeNONSdlzSW66alcayjHrb4+5IYGV9vzMk7qGLHg" + ""
//			+ "ZX++HJBUKkb1piqATvPJNFlhf1vJAgMBAAECgYA736xhG0oL3EkN9yhx8zG/5RP/" + ""
//			+ "WJzoQOByq7pTPCr4m/Ch30qVerJAmoKvpPumN+h1zdEBk5PHiAJkm96sG/PTndEf" + ""
//			+ "kZrAJ2hwSBqptcABYk6ED70gRTQ1S53tyQXIOSjRBcugY/21qeswS3nMyq3xDEPK" + ""
//			+ "XpdyKPeaTyuK86AEkQJBAM1M7p1lfzEKjNw17SDMLnca/8pBcA0EEcyvtaQpRvaL" + ""
//			+ "n61eQQnnPdpvHamkRBcOvgCAkfwa1uboru0QdXii/gUCQQDGmkP+KJPX9JVCrbRt" + ""
//			+ "7wKyIemyNM+J6y1ZBZ2bVCf9jacCQaSkIWnIR1S9UM+1CFE30So2CA0CfCDmQy+y" + ""
//			+ "7A31AkB8cGFB7j+GTkrLP7SX6KtRboAU7E0q1oijdO24r3xf/Imw4Cy0AAIx4KAu" + ""
//			+ "L29GOp1YWJYkJXCVTfyZnRxXHxSxAkEAvO0zkSv4uI8rDmtAIPQllF8+eRBT/deD" + ""
//			+ "JBR7ga/k+wctwK/Bd4Fxp9xzeETP0l8/I+IOTagK+Dos8d8oGQUFoQJBAI4Nwpfo" + ""
//			+ "MFaLJXGY9ok45wXrcqkJgM+SN6i8hQeujXESVHYatAIL/1DgLi+u46EFD69fw0w+" + "" + "c7o0HLlMsYPAzJw="
//			+ "";
	private static String PRIVATE_KEY = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAMCI/NkVakRpQnvj" +
		"r2MpDizFGX9RSo3k1SbhR/lPb0pIElmhtX2hrAoIccDY8LEkCRSkZd1ogstbYNKb" +
		"qQfVYwONE18peODrYK1l7RRmLd2C/D6p/5WruB0dceEa2CJvm6sPQvjgIcorfuIb" +
		"wWcnmmEbEVSSfAsSDEEyepYvshytAgMBAAECgYA0YxGpX5JSmvFkFEeci4c310lg" +
		"wxWuFh2JPrL+uwZlB8c2bDGdxWeyMyoAdwbGrC9sVm8BF3a/LlXh+Ei9wOn3jD9D" +
		"i4nRt2SAjiPz9bnQBi8klZY/KakOhAVuRzWLYpw1eJkGQr9l2JVcGmGKuxmGUJXy" +
		"psV+ZK2FvcCmS9TUYQJBAO7jUdfgWIQMvq2U7aMtApYovCFIPnMUcIPaxg+NnZEa" +
		"2j8XZQn0G1GJAyYdP8DYsXMtOv3dPBKYyGA1JCg5iN8CQQDOU6iOKNLPEA6cQZxX" +
		"/tH3hOm3TfSREgrRlIvCiU34AXI5DLMFh7d3JMb1rM8hU4Zk/L8C371HoqP1NWxW" +
		"U+/zAkEAm2lv+U2OyHmyIyNcZmgF4sKlgdGKLzwA5wcRC1VKSPh7O8hwh0Uz8Jt+" +
		"XPFyZHeVGiU216lyR3b2BQENgM3NaQJBALVa2WxjO7Dfd0gnb4n1JI+BF4GUluSw" +
		"zCudex86WvskIkc8/1pcCYpDHFKzW7j6VsYLVsXq7c+6iI0vMRPMYbsCQQCZDLoO" +
		"SMc2TIs002RmkmpIndmlSe6GDDk+7wqC+CN10iubBENvgMyGzQJIt/yoXwyc984y" +
		"AWDJ788bXcFSFd24"
			+ "";

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}

	private void initView()
	{
		btn1 = (Button) findViewById(R.id.btn1);
		btn2 = (Button) findViewById(R.id.btn2);
		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);

		et1 = (EditText) findViewById(R.id.et1);
		et2 = (EditText) findViewById(R.id.et2);
		et3 = (EditText) findViewById(R.id.et3);
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		// 加密
		case R.id.btn1:
			String source = et1.getText().toString().trim();
			try
			{
				// 从字符串中得到公钥
				 PublicKey publicKey = RSAUtils.loadPublicKey(PUCLIC_KEY);
				// 从文件中得到公钥
//				InputStream inPublic = getResources().getAssets().open("rsa_public_key.pem");
//				PublicKey publicKey = RSAUtils.loadPublicKey(inPublic);
				// 加密
				byte[] encryptByte = RSAUtils.encryptData(source.getBytes(), publicKey);
				// 为了方便观察吧加密后的数据用base64加密转一下，要不然看起来是乱码,所以解密是也是要用Base64先转换
				String afterencrypt = Base64Utils.encode(encryptByte);
				et2.setText(afterencrypt);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			break;
		// 解密
		case R.id.btn2:
//			String encryptContent = et2.getText().toString().trim();
			String encryptContent ="cGDrpEIZ0OsON5BDFGm0crOChm9Tjrmj2PsX1j+hHb5rA50dzOmFxnNPBblSF+tTswZ266gUltk7CxiIsnvPFuoZXjwPQthBEgEGCUm/K3QQNwFpnWcXCoQv59G/mTl+J+3b/y1IAuekOcgJz8t1Qf5BZv+kMVbWhvfLFkvVebQ=";
			try
			{
				// 从字符串中得到私钥
				 PrivateKey privateKey = RSAUtils.loadPrivateKey(PRIVATE_KEY);
				// 从文件中得到私钥
//				InputStream inPrivate = getResources().getAssets().open("pkcs8_rsa_private_key.pem");
//				PrivateKey privateKey = RSAUtils.loadPrivateKey(inPrivate);
				// 因为RSA加密后的内容经Base64再加密转换了一下，所以先Base64解密回来再给RSA解密
				byte[] decryptByte = RSAUtils.decryptData(Base64Utils.decode(encryptContent), privateKey);
				String decryptStr = new String(decryptByte);
				et3.setText(decryptStr);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
	}

}
