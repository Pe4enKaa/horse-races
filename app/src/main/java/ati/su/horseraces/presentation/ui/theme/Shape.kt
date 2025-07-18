package ati.su.horseraces.presentation.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults.textFieldColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Shapes
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

val DefaultShape = RoundedCornerShape(8.dp)

val Shapes = Shapes(
    extraSmall = RoundedCornerShape(4.dp),
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(24.dp),
    extraLarge = RoundedCornerShape(32.dp)
)


@Composable
fun DefaultImageButtonTheme() = buttonColors(
    containerColor = MaterialTheme.colorScheme.background,
    contentColor = MaterialTheme.colorScheme.primary,
    // disabledBackgroundColor = MaterialTheme.colorScheme.background,
    disabledContentColor = MaterialTheme.colorScheme.primary
)

@Composable
fun DefaultButtonWithBorderPrimaryTheme() = buttonColors(
    containerColor = MaterialTheme.colorScheme.background,
    contentColor = MaterialTheme.colorScheme.primary,
    disabledContainerColor = MaterialTheme.colorScheme.background,
    // disabledBackgroundColor = MaterialTheme.colorScheme.background,
    disabledContentColor = MaterialTheme.colorScheme.primary
)

@Composable
fun DefaultButtonTheme() = buttonColors(
    containerColor = MaterialTheme.colorScheme.primary,
    contentColor = MaterialTheme.colorScheme.background,
    // disabledBackgroundColor = MaterialTheme.colorScheme.background,
    disabledContentColor = MaterialTheme.colorScheme.primary
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTextFieldTheme() = textFieldColors(
    focusedContainerColor = TextFieldColor,
    unfocusedContainerColor = TextFieldColor,
    cursorColor = MaterialTheme.colorScheme.onBackground,
    focusedIndicatorColor = Color.Transparent,
    unfocusedIndicatorColor = Color.Transparent,
    disabledContainerColor = TextFieldColor,
    disabledTextColor = MaterialTheme.colorScheme.onBackground,
    disabledIndicatorColor = Color.Transparent,
)


@Composable
fun DefaultCheckBoxTheme() = CheckboxDefaults.colors(
    checkedColor = MaterialTheme.colorScheme.primary,
    uncheckedColor = MaterialTheme.colorScheme.primary,
    checkmarkColor = MaterialTheme.colorScheme.background
)


@Composable
fun DefaultCardColorsTheme() = CardDefaults.cardColors(
    containerColor = MaterialTheme.colorScheme.background,
    contentColor = MaterialTheme.colorScheme.onBackground,
    disabledContainerColor = MaterialTheme.colorScheme.background,
    disabledContentColor = MaterialTheme.colorScheme.onBackground,
)

@Composable
fun CustomCardColorsTheme(containerColor: Color, contentColor: Color) = CardDefaults.cardColors(
    containerColor = containerColor,
    contentColor = contentColor,
    disabledContainerColor = containerColor,
    disabledContentColor = contentColor,
)


@Composable
fun DefaultNavigationBarItemTheme() = NavigationBarItemDefaults.colors(
    selectedIconColor = SelectedIconColor,
    unselectedIconColor = UnSelectedIconColor,
    unselectedTextColor = UnSelectedTextColor,
    selectedTextColor = SelectedTextColor,
    indicatorColor = IndicatorColor,
)


@Composable
fun TextFieldWithTransparentTheme() = TextFieldDefaults.colors(
    // cursorColor = Color.Transparent,
    focusedIndicatorColor = Color.Transparent,
    unfocusedIndicatorColor = Color.Transparent,
    disabledIndicatorColor = Color.Transparent,
    focusedContainerColor = Color.Transparent,
    disabledContainerColor = Color.Transparent,
    unfocusedContainerColor = Color.Transparent
)
