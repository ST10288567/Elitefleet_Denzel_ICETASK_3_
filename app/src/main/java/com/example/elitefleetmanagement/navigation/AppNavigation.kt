package com.example.elitefleetmanagement.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.elitefleetmanagement.ui.screens.BookingDetailsScreen
import com.example.elitefleetmanagement.ui.screens.BookingFormScreen
import com.example.elitefleetmanagement.ui.screens.BookingScreen
import com.example.elitefleetmanagement.ui.screens.DashboardScreen
import com.example.elitefleetmanagement.ui.screens.InventoryScreen
import com.example.elitefleetmanagement.ui.screens.LoginScreen
import com.example.elitefleetmanagement.ui.screens.VehicleDetailsScreen
import com.example.elitefleetmanagement.ui.screens.VehicleFormScreen
import com.example.elitefleetmanagement.ui.screens.WelcomeScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.WELCOME
    ) {
        composable(Routes.WELCOME) {
            WelcomeScreen(
                onGetStarted = {
                    navController.navigate(Routes.LOGIN)
                }
            )
        }

        composable(Routes.LOGIN) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Routes.DASHBOARD) {
                        popUpTo(Routes.WELCOME) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(Routes.DASHBOARD) {
            DashboardScreen(
                onInventoryClick = {
                    navController.navigate(Routes.INVENTORY)
                },
                onBookingsClick = {
                    navController.navigate(Routes.BOOKINGS)
                }
            )
        }

        composable(Routes.INVENTORY) {
            InventoryScreen(
                onAddVehicle = {
                    navController.navigate(Routes.ADD_VEHICLE)
                },
                onVehicleClick = { vehicleId ->
                    navController.navigate("${Routes.VEHICLE_DETAILS}/$vehicleId")
                }
            )
        }

        composable(Routes.ADD_VEHICLE) {
            VehicleFormScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = "${Routes.VEHICLE_DETAILS}/{vehicleId}",
            arguments = listOf(
                navArgument("vehicleId") {
                    type = NavType.IntType
                }
            )
        ) { entry ->
            val vehicleId = entry.arguments?.getInt("vehicleId") ?: 0

            VehicleDetailsScreen(
                vehicleId = vehicleId,
                onBack = {
                    navController.popBackStack()
                },
                onEdit = { id ->
                    navController.navigate("${Routes.EDIT_VEHICLE}/$id")
                }
            )
        }

        composable(
            route = "${Routes.EDIT_VEHICLE}/{vehicleId}",
            arguments = listOf(
                navArgument("vehicleId") {
                    type = NavType.IntType
                }
            )
        ) { entry ->
            val vehicleId = entry.arguments?.getInt("vehicleId") ?: 0

            VehicleFormScreen(
                vehicleId = vehicleId,
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(Routes.BOOKINGS) {
            BookingScreen(
                onAddBooking = {
                    navController.navigate(Routes.ADD_BOOKING)
                },
                onBookingClick = { bookingId ->
                    navController.navigate("${Routes.BOOKING_DETAILS}/$bookingId")
                }
            )
        }

        composable(Routes.ADD_BOOKING) {
            BookingFormScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = "${Routes.BOOKING_DETAILS}/{bookingId}",
            arguments = listOf(
                navArgument("bookingId") {
                    type = NavType.IntType
                }
            )
        ) { entry ->
            val bookingId = entry.arguments?.getInt("bookingId") ?: 0

            BookingDetailsScreen(
                bookingId = bookingId,
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}