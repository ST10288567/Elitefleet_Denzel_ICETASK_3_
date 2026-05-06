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
import com.example.elitefleetmanagement.ui.screens.ClaimsScreen
import com.example.elitefleetmanagement.ui.screens.DashboardScreen
import com.example.elitefleetmanagement.ui.screens.InventoryScreen
import com.example.elitefleetmanagement.ui.screens.LoginScreen
import com.example.elitefleetmanagement.ui.screens.ProfileScreen
import com.example.elitefleetmanagement.ui.screens.ReportsScreen
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

        /* =========================
           WELCOME SCREEN
        ========================= */

        composable(Routes.WELCOME) {
            WelcomeScreen(
                onGetStarted = {
                    navController.navigate(Routes.LOGIN)
                }
            )
        }

        /* =========================
           LOGIN SCREEN
        ========================= */

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

        /* =========================
           DASHBOARD SCREEN
        ========================= */

        composable(Routes.DASHBOARD) {
            DashboardScreen(
                onInventoryClick = {
                    navController.navigate(Routes.INVENTORY)
                },

                onBookingsClick = {
                    navController.navigate(Routes.BOOKINGS)
                },

                onClaimsClick = {
                    navController.navigate(Routes.CLAIMS)
                },

                onReportsClick = {
                    navController.navigate(Routes.REPORTS)
                },

                onProfileClick = {
                    navController.navigate(Routes.PROFILE)
                }
            )
        }

        /* =========================
           INVENTORY SCREEN
        ========================= */

        composable(Routes.INVENTORY) {
            InventoryScreen(
                onAddVehicle = {
                    navController.navigate(Routes.ADD_VEHICLE)
                },

                onVehicleClick = { vehicleId ->
                    navController.navigate(
                        "${Routes.VEHICLE_DETAILS}/$vehicleId"
                    )
                }
            )
        }

        /* =========================
           ADD VEHICLE SCREEN
        ========================= */

        composable(Routes.ADD_VEHICLE) {
            VehicleFormScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        /* =========================
           VEHICLE DETAILS SCREEN
        ========================= */

        composable(
            route = "${Routes.VEHICLE_DETAILS}/{vehicleId}",

            arguments = listOf(
                navArgument("vehicleId") {
                    type = NavType.IntType
                }
            )
        ) { entry ->

            val vehicleId =
                entry.arguments?.getInt("vehicleId") ?: 0

            VehicleDetailsScreen(
                vehicleId = vehicleId,

                onBack = {
                    navController.popBackStack()
                },

                onEdit = { id ->
                    navController.navigate(
                        "${Routes.EDIT_VEHICLE}/$id"
                    )
                }
            )
        }

        /* =========================
           EDIT VEHICLE SCREEN
        ========================= */

        composable(
            route = "${Routes.EDIT_VEHICLE}/{vehicleId}",

            arguments = listOf(
                navArgument("vehicleId") {
                    type = NavType.IntType
                }
            )
        ) { entry ->

            val vehicleId =
                entry.arguments?.getInt("vehicleId") ?: 0

            VehicleFormScreen(
                vehicleId = vehicleId,

                onBack = {
                    navController.popBackStack()
                }
            )
        }

        /* =========================
           BOOKINGS SCREEN
        ========================= */

        composable(Routes.BOOKINGS) {
            BookingScreen(

                onAddBooking = {
                    navController.navigate(
                        Routes.ADD_BOOKING
                    )
                },

                onBookingClick = { bookingId ->
                    navController.navigate(
                        "${Routes.BOOKING_DETAILS}/$bookingId"
                    )
                }
            )
        }

        /* =========================
           ADD BOOKING SCREEN
        ========================= */

        composable(Routes.ADD_BOOKING) {
            BookingFormScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        /* =========================
           BOOKING DETAILS SCREEN
        ========================= */

        composable(
            route = "${Routes.BOOKING_DETAILS}/{bookingId}",

            arguments = listOf(
                navArgument("bookingId") {
                    type = NavType.IntType
                }
            )
        ) { entry ->

            val bookingId =
                entry.arguments?.getInt("bookingId") ?: 0

            BookingDetailsScreen(
                bookingId = bookingId,

                onBack = {
                    navController.popBackStack()
                }
            )
        }

        /* =========================
           CLAIMS SCREEN
        ========================= */

        composable(Routes.CLAIMS) {
            ClaimsScreen()
        }

        /* =========================
           REPORTS SCREEN
        ========================= */

        composable(Routes.REPORTS) {
            ReportsScreen()
        }

        /* =========================
           PROFILE SCREEN
        ========================= */

        composable(Routes.PROFILE) {
            ProfileScreen()
        }
    }
}