package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityCheckoutBinding
import com.google.android.gms.pay.Pay
import com.google.android.gms.pay.PayApiAvailabilityStatus
import com.google.android.gms.pay.PayClient
import java.util.Date
import java.util.UUID
import kotlin.random.Random

class CheckoutActivity : AppCompatActivity() {

    private val token ="eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJjdWVudGEtYWRkLXdhbGxldEBkb2d3b29kLXRlcnJhLTE4MDYxNi5pYW0uZ3NlcnZpY2VhY2NvdW50LmNvbSIsImF1ZCI6Imdvb2dsZSIsIm9yaWdpbnMiOlsiaHR0cDovL2xvY2FsaG9zdDozMDAwIl0sInR5cCI6InNhdmV0b3dhbGxldCIsInBheWxvYWQiOnsiZ2VuZXJpY09iamVjdHMiOlt7ImlkIjoiMzM4ODAwMDAwMDAyMjMyMzM5MS5jb2RlbGFiX29iamVjdCIsImNsYXNzSWQiOiIzMzg4MDAwMDAwMDIyMzIzMzkxLmNvZGVsYWJfY2xhc3MiLCJnZW5lcmljVHlwZSI6IkdFTkVSSUNfVFlQRV9VTlNQRUNJRklFRCIsImhleEJhY2tncm91bmRDb2xvciI6IiM0Mjg1ZjQiLCJsb2dvIjp7InNvdXJjZVVyaSI6eyJ1cmkiOiJodHRwczovL3N0b3JhZ2UuZ29vZ2xlYXBpcy5jb20vd2FsbGV0LWxhYi10b29scy1jb2RlbGFiLWFydGlmYWN0cy1wdWJsaWMvcGFzc19nb29nbGVfbG9nby5qcGcifX0sImNhcmRUaXRsZSI6eyJkZWZhdWx0VmFsdWUiOnsibGFuZ3VhZ2UiOiJlbi1VUyIsInZhbHVlIjoiR29vZ2xlIEkvTyAnMjIifX0sInN1YmhlYWRlciI6eyJkZWZhdWx0VmFsdWUiOnsibGFuZ3VhZ2UiOiJlbi1VUyIsInZhbHVlIjoiQXR0ZW5kZWUifX0sImhlYWRlciI6eyJkZWZhdWx0VmFsdWUiOnsibGFuZ3VhZ2UiOiJlbi1VUyIsInZhbHVlIjoiTWFyaWEgQ3Jpc3RpbmEifX0sImJhcmNvZGUiOnsidHlwZSI6IlFSX0NPREUiLCJ2YWx1ZSI6IjMzODgwMDAwMDAwMjIzMjMzOTEuY29kZWxhYl9vYmplY3QifSwiaGVyb0ltYWdlIjp7InNvdXJjZVVyaSI6eyJ1cmkiOiJodHRwczovL3N0b3JhZ2UuZ29vZ2xlYXBpcy5jb20vd2FsbGV0LWxhYi10b29scy1jb2RlbGFiLWFydGlmYWN0cy1wdWJsaWMvZ29vZ2xlLWlvLWhlcm8tZGVtby1vbmx5LmpwZyJ9fSwidGV4dE1vZHVsZXNEYXRhIjpbeyJoZWFkZXIiOiJQT0lOVFMiLCJib2R5IjoiMTIzNCIsImlkIjoicG9pbnRzIn0seyJoZWFkZXIiOiJDT05UQUNUUyIsImJvZHkiOiIyMCIsImlkIjoiY29udGFjdHMifV19XX0sImlhdCI6MTcxMTU2MTUxMH0.C4xCYaU0g7JOCKZP8xGSrsh9MBgpSqUSq7nMdjiClWPgxd_RsQGfmW1EKZAPrsOT6ZOFpH1kr77g_3A2iAsqsEY0UMMtBRjG-ZITJNrAohsolXQXLSgRDSjFmVvsnpw7COjOQ5f2upLR3ti-bGG06bE8YqkGDthRMswEfkCqa5mMlTXTsoeSvEpXLTMCSijDDUbkqFhr_5mR4YWpfEP06CDUHxWb7wtacc22ER03ivnc-BdbgLZEBlDbc6DjPjd3GguY4aZ2AJTaQkD0wKWTMB-PcY-HmVCD8OL33imssTQ9WH-EtBX2Qq7gztrFZPjPjWMN3nHaUma5X2bQ-U3YjA"
    private val addToGoogleWalletRequestCode = 1000
    private lateinit var layout: ActivityCheckoutBinding
    private lateinit var addToGoogleWalletButton: View
    private lateinit var walletClient :PayClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        walletClient = Pay.getClient(this)
        layout = ActivityCheckoutBinding.inflate(layoutInflater)

        setContentView(layout.root)

        addToGoogleWalletButton = layout.addToGoogleWalletButton.root
        addToGoogleWalletButton.setOnClickListener {
            walletClient.savePasses(newObjectJson, this, addToGoogleWalletRequestCode)
        }
        fetchGoogleWalletApiStatus()
    }

    private fun fetchGoogleWalletApiStatus(){
        walletClient.getPayApiAvailabilityStatus(PayClient.RequestType.SAVE_PASSES)
            .addOnSuccessListener { status ->
                if(status == PayApiAvailabilityStatus.AVAILABLE)
                    layout.passContainer.visibility = View.VISIBLE
            }
            .addOnFailureListener{
            }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == addToGoogleWalletRequestCode){
            when(resultCode){
                RESULT_OK->{
                    //No-op
                }
                RESULT_CANCELED ->{
                    //No-op
                }
                PayClient.SavePassesResult.SAVE_ERROR -> data?.let{ intentData ->{
                    val apiErrorMessage = intentData.getStringExtra(PayClient.EXTRA_API_ERROR_MESSAGE)
                    Log.e("SavePassesResult", apiErrorMessage.toString())
                }
                }
                else -> {
                    //Handle all non-actionables
                }
            }
        }
    }
    private val issuerEmail = "cuenta-add-wallet@dogwood-terra-180616.iam.gserviceaccount.com"
    private val issuerId = "3388000000022323391"
    private val passClass = "3388000000022323391.codelab_class"
    private val passId = UUID.randomUUID().toString()
    private val newObjectJson = """
    {
      "iss": "$issuerEmail",
      "aud": "google",
      "typ": "savetowallet",
      "iat": ${Date().time / 1000L},
      "origins": [],
      "payload": {
        "genericObjects": [
          {
            "id": "$issuerId.$passId",
            "classId": "$passClass",
            "genericType": "GENERIC_TYPE_UNSPECIFIED",
            "hexBackgroundColor": "#4285f4",
            "logo": {
              "sourceUri": {
                "uri": "https://as2.ftcdn.net/v2/jpg/04/36/71/53/1000_F_436715382_ooUpdqTNe7Mhh2WbNfKAKDutzD9FfqHJ.jpg"
              }
            },
            "cardTitle": {
              "defaultValue": {
                "language": "en",
                "value": "New Store"
              }
            },
            "subheader": {
              "defaultValue": {
                "language": "en",
                "value": "Attendee"
              }
            },
            "header": {
              "defaultValue": {
                "language": "en",
                "value": "Maria Cristina"
              }
            },
            "barcode": {
              "type": "QR_CODE",
              "value": "$passId"
            },
            "heroImage": {
              "sourceUri": {
                "uri": "https://www.color-hex.com/palettes/1041740.png"
              }
            },
            "textModulesData": [
              {
                "header": "POINTS",
                "body": "${Random.nextInt(0, 9999)}",
                "id": "points"
              },
              {
                "header": "CONTACTS",
                "body": "${Random.nextInt(1, 99)}",
                "id": "contacts"
              }
            ]
          }
        ]
      }
    }
    """

}