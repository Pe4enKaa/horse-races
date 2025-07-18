package ati.su.horseraces.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ati.su.horseraces.R

@Composable
fun latoTypography(): Typography {
        val lato = FontFamily(
                Font(
                        resId = R.font.lato_regular,
                        weight = FontWeight.Normal,
                        style = FontStyle.Normal
                ),
                Font(
                        resId = R.font.lato_thin,
                        weight = FontWeight.Thin,
                        style = FontStyle.Normal
                ),
                Font(
                        resId = R.font.lato_italic,
                        weight = FontWeight.Normal,
                        style = FontStyle.Italic
                ),
                Font(
                        resId = R.font.lato_light_italic,
                        weight = FontWeight.Light,
                        style = FontStyle.Italic
                ),
                Font(
                        resId = R.font.lato_thin_italic,
                        weight = FontWeight.Thin,
                        style = FontStyle.Italic
                ),
                Font(
                        resId = R.font.lato_bold_italic,
                        weight = FontWeight.Bold,
                        style = FontStyle.Italic
                ),
                Font(
                        resId = R.font.lato_black_italic,
                        weight = FontWeight.Black,
                        style = FontStyle.Italic
                ),
                Font(
                        resId = R.font.lato_bold,
                        weight = FontWeight.Bold,
                        style = FontStyle.Normal
                ),
                Font(
                        resId = R.font.lato_black,
                        weight = FontWeight.Black,
                        style = FontStyle.Normal
                ),
                Font(
                        resId = R.font.lato_light,
                        weight = FontWeight.Light,
                        style = FontStyle.Normal
                )
        )

        return Typography(
                headlineSmall = TextStyle(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 24.sp,
                        fontFamily = lato
                ),
                titleLarge = TextStyle(
                        fontWeight = FontWeight.Normal,
                        fontSize = 18.sp,
                        fontFamily = lato
                ),
                bodyLarge = TextStyle(
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                        fontFamily = lato
                ),
                bodyMedium = TextStyle(
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                        fontFamily = lato
                ),
                labelMedium = TextStyle(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp,
                        fontFamily = lato
                ),
        )
}