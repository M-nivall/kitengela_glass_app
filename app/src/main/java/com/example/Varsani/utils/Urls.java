package com.example.Varsani.utils;

public class Urls {


    public static String ipAddress = "http://192.168.129.93/kitengelaglass/";
    //public static String ipAddress = "https://1b557a947286.ngrok-free.app/woodways/";

    private static final String ROOT_URL = ipAddress + "android_files/";
    public static final String ROOT_URL_IMAGES = ipAddress + "upload_products/";


    public static final String URL_PRINT = ipAddress + "print_pdf.php";
    public static final String UEL_FEEDBACK = ROOT_URL + "client/get_feedback.php";
    public static final String UEL_FEEDBACK_SEND = ROOT_URL + "client/send_feedback.php";


    public static final String UEL_STAFF_SEND_FEEDBACK = ROOT_URL + "client/staff_sendfeedback.php";
    public static final String UEL_STAFF_FEEDBACK = ROOT_URL + "client/getstafffeedback.php";

    //  products
    public static final String URL_GET_PRODUCTS=ROOT_URL + "client/products.php";
    public static final String URL_ADD_CART = ROOT_URL + "client/add_to_cart.php";
    public static final String URL_GET_CART = ROOT_URL + "client/cart.php";
    public static final String URL_UPDATE_CART = ROOT_URL + "client/car_update.php";
    public static final String URL_REMOVE_ITEM = ROOT_URL + "client/cart_remove.php";

    //services
    public static final String URL_GET_SERVICES=ROOT_URL + "client/services.php";
    public static final String URL_ADD_CART2 = ROOT_URL + "client/add_to_cart2.php";
    public static final String URL_GET_CART2 = ROOT_URL + "client/cart2.php";
    public static final String URL_REMOVE_BOOKING = ROOT_URL + "client/booking_remove.php";
    public static final String URL_SUBMIT_REQUEST = ROOT_URL + "client/submit_request.php";

    // shipping
    public static final String URL_GET_COUNTIES = ROOT_URL + "client/counties.php";
    public static final String URL_GET_TOWNS = ROOT_URL + "client/towns.php";
    public static final String URL_DELIVERY_DETAILS = ROOT_URL + "client/delivery_details.php";

    // checkout
    public static final String URL_GET_CHECKOUT_TOTAL = ROOT_URL + "client/checkout_cost.php";
    // user
    public static final String URL_REG = ROOT_URL + "client/register.php";
    public static final String URL_LOGIN = ROOT_URL + "client/login.php";
    public static final String URL_RESET = ROOT_URL + "client/forgotpass.php";
    public static final String URL_RESET2 = ROOT_URL + "client/resetpass.php";
    //    SUPPLIERS
    public static final String URL_REG_SUPPLIERS = ROOT_URL + "supplier/reg_supplier.php";
    public static final String URL_MY_REQUESTS = ROOT_URL + "supplier/my_requests.php";
    public static final String URL_BID = ROOT_URL + "supplier/bid.php";
    public static final String URL_ACCEPT = ROOT_URL + "supplier/approve_items.php";
    public static final String URL_PAYMENT_DETAILS= ROOT_URL+"supplier/payment_details.php";
    public static final String URL_SUPPLY_HISTORY= ROOT_URL+"supplier/supply_history.php";
    // orders
    public static final String URL_SUBMIT_ORDER = ROOT_URL + "client/submit_order.php";
    public static final String URL_GET_ORDERS = ROOT_URL + "client/order_history.php";
    public static final String URL_GET_ORDER_ITEMS = ROOT_URL + "client/order_items.php";
    public static final String URL_GET_ORDER_ITEMS2 = ROOT_URL + "client/order_items2.php";
    public static final String URL_MARK_DELIVERED = ROOT_URL + "client/mark_delivered.php";
    public static final String URL_MARK_REJECTED = ROOT_URL + "client/mark_rejected.php";
    public static final String URL_MARK_COMPLETE = ROOT_URL + "client/mark_completed.php";

    //invoices
    public static final String URL_GET_INVOICE = ROOT_URL + "client/invoice_history.php";
    public static final String URL_SUBMIT_INVOICE = ROOT_URL + "client/submit_invoice.php";

    //Staff
    public static final String URL_STAFF_LOGIN = ROOT_URL + "staff_login.php";
    //Finance

    public static final String URL_GET_CLIENT_ITEMS = ROOT_URL + "client_item.php";
    public static final String URL_GET_APPROVE_ORDERS=ROOT_URL + "finance/approve_order.php";
    public static final String URL_APPROVED_ORDERS = ROOT_URL + "finance/approved_orders.php";
    public static final String URL_NEW_BOOKING_PAYMENTS = ROOT_URL + "finance/new_bookings.php";
    public static final String URL_NEW_SERV_PAYMENTS = ROOT_URL + "finance/new_serv_payments.php";
    public static final String URL_APPROVE_BOOKING_PAYMENTS = ROOT_URL + "finance/approve_booking_payments.php";
    public static final String URL_APPROVE_SERV_PAYMENTS = ROOT_URL + "finance/approve_serv_payments.php";
    public static final String URL_APPROVED_SERV_PAYMENTS = ROOT_URL + "finance/approved_serv_payments.php";
    public static final String URL_SUPPLY_PAYMENTS = ROOT_URL + "finance/supply_payments.php";
    public static final String URL_SUPPLY_PAYMENTS2 = ROOT_URL + "finance/supply_payments2.php";
    public static final String URL_PAY_SUPPLIER = ROOT_URL + "finance/pay_supplier.php";

    //shipping mrg
    public static final String URL_ORDERS_TO_SHIP = ROOT_URL + "ship_mrg/orders_to_ship.php";
    public static final String URL_APPROVE_MATERIALS=ROOT_URL + "ship_mrg/approve_materials.php";
    public static final String URL_GET_DRIVERS = ROOT_URL + "ship_mrg/get_drivers.php";
    public static final String URL_SHIP_ORDER = ROOT_URL + "ship_mrg/ship_order.php";
    public static final String URL_SHIPPING_ORDERS = ROOT_URL + "ship_mrg/shipping_orders.php";
    public static final String URL_APPROVE_TENDER = ROOT_URL + "ship_mrg/approve_tender.php";

    public static final String URL_APPROVE_BID = ROOT_URL + "ship_mrg/approve_bid.php";


    //Service   Manager
    public static final String URL_QUOTATION_REQUEST = ROOT_URL + "serv_mrg/quot_requests.php";
    public static final String URL_QUOTATION_ITEMS = ROOT_URL + "quot_items.php";
    public static final String URL_GET_TECHNICIANS = ROOT_URL + "serv_mrg/get_technicians.php";
    public static final String URL_ASSIGN_TECH = ROOT_URL + "serv_mrg/assign_tech.php";

    //technician
    public static final String URL_GET_ASSIGNED_SITES = ROOT_URL + "technician/assigned_orders.php";
    public static final String URL_GET_ASSIGNED_SERVICES = ROOT_URL + "technician/assigned_services.php";
    public static final String URL_SEND_QUOTATION = ROOT_URL + "technician/send_quotation.php";
    public static final String URL_CONFIRM_COMPLETION = ROOT_URL + "technician/confirm_completion.php";

    // Driver
    public static final String URL_GET_ASSIGNED_ORDERS = ROOT_URL + "driver/assigned_orders.php";
    public static final String URL_GET_MARKED_ORDERS = ROOT_URL + "driver/arrived_orders.php";
    public static final String URL_GET_DELIVERED_ORDERS = ROOT_URL + "driver/delivered_orders.php";
    public static final String URL_MARK_ORDER = ROOT_URL + "driver/mark_arrived.php";
    //Store mrg
    public static final String URL_GET_STOCK = ROOT_URL + "stock_mrg/stock.php";
    public static final String URL_ADD_STOCK = ROOT_URL + "stock_mrg/add_stock.php";
    public static final String URL_SUPPLIER = ROOT_URL + "stock_mrg/suppliers.php";
    public static final String URL_SEND_REQUEST = ROOT_URL + "stock_mrg/send_requests.php";
    public static final String URL_NEW_ORDERS=ROOT_URL + "finance/new_orders.php";
    public static final String URL_REQUESTMATERIALS=ROOT_URL + "stock_mrg/material_request.php";
    public static final String URL_REQUESTS=ROOT_URL + "stock_mrg/request.php";
    public static final String URL_SERVICE_COMPLETED= ROOT_URL+"serv_mrg/service_history.php";
    public static final String URL_GET_TOOLS=ROOT_URL + "stock_mrg/tools.php";
}
