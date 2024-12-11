package com.example.ejer3_stickyhead

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ejer3_stickyhead.ui.theme.Contact
import com.example.ejer3_stickyhead.ui.theme.Ejer3_StickyHeadTheme
import kotlin.text.first
import kotlin.text.forEach
import kotlin.text.groupBy
import kotlin.text.uppercaseChar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ejer3_StickyHeadTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column (modifier = Modifier.fillMaxSize().background(Color.DarkGray)){
        Spacer(modifier = Modifier.height(40.dp))
        ContactListScreen()
    }
}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ContactListScreen() {
    val contacts = listOf(
        Contact("Alvaro", "607-456-729"),
        Contact("Bernardo", "958-652-320"),
        Contact("Carlos", "555-444-333"),
        Contact("David", "111-222-333"),
        Contact("Ernesto", "444-555-888")
    )

    val groupedContacts = contacts.groupBy { it.name.first().uppercaseChar() }

    LazyColumn(modifier = Modifier.fillMaxSize().background(Color.White)) {
        groupedContacts.forEach { (initial, contactsForInitial) ->
            stickyHeader {
                Surface(
                    color = Color.LightGray,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text(text = initial.toString(), style = MaterialTheme.typography.bodySmall)
                }
            }
            items(contactsForInitial) { contact ->
                ContactItem(contact)
            }
        }
    }
}

@Composable
fun ContactItem(contact: Contact) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = contact.name, fontSize = 18.sp,color = Color.Black, style = MaterialTheme.typography.bodyLarge)
        Text(text = contact.phoneNumber,color = Color.Black, style = MaterialTheme.typography.bodyMedium)
    }
}
