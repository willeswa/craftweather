import com.wanjala.weathercraft.data.models.City
import com.wanjala.weathercraft.data.models.Coordinates

val predefinedCities = listOf(
    // United States
    City(Coordinates(40.7128, -74.0060), "New York", "US", "ChIJqaUj8fBLzEwRZ5UY3sHGz90", "America/New_York", -300),
    City(Coordinates(34.0522, -118.2437), "Los Angeles", "US", "ChIJE9on3F3HwoAR9AhGJW_fL-I", "America/Los_Angeles", -480),
    City(Coordinates(37.7749, -122.4194), "San Francisco", "US", "ChIJIQBpAG2ahYAR_6128GcTUEo", "America/Los_Angeles", -480),
    City(Coordinates(35.2271, -80.8431), "Charlotte", "US", "ChIJgRo4_MQfVIgRZNFDv-ZQRog", "America/New_York", -300),

    // Africa
    City(Coordinates(-1.286389, 36.817223), "Nairobi", "KE", "ChIJp0lN2HIRLxgRTJKXslQCz_c", "Africa/Nairobi", 180),
    City(Coordinates(-26.2041, 28.0473), "Johannesburg", "ZA", "ChIJL-QugkShgR4RQ7BgbH6kZGs", "Africa/Johannesburg", 120),
    City(Coordinates(6.5244, 3.3792), "Lagos", "NG", "ChIJwQWlB_0nOxARVhKDl7xSxEo", "Africa/Lagos", 60),
    City(Coordinates(30.0444, 31.2357), "Cairo", "EG", "ChIJ664LBRH_AhQRxJ2-1TMwL-I", "Africa/Cairo", 120),
    City(Coordinates(5.6037, -0.1870), "Accra", "GH", "ChIJN5tT8PUe3w8R7n2_fBfxz7Q", "Africa/Accra", 0),
    City(Coordinates(-4.0435, 39.6682), "Mombasa", "KE", "ChIJVdVy_64QLxgRzRwv0v6KvlI", "Africa/Nairobi", 180),
    City(Coordinates(-15.3875, 28.3228), "Lusaka", "ZM", "ChIJzTBnxNDaQRkRGd-QG0yEQzA", "Africa/Lusaka", 120),
    City(Coordinates(14.7167, -17.4677), "Dakar", "SN", "ChIJEa0e1ncuQhIR5k3oiQu9-wU", "Africa/Dakar", 0),

    // Europe
    City(Coordinates(51.5074, -0.1278), "London", "GB", "ChIJdd4hrwug2EcRmSrV3Vo6llI", "Europe/London", 0),
    City(Coordinates(48.8566, 2.3522), "Paris", "FR", "ChIJD7fiBh9u5kcRYJSMaMOCCwQ", "Europe/Paris", 60),
    City(Coordinates(52.5200, 13.4050), "Berlin", "DE", "ChIJAVkDPzdOqEcRcDteW0YgIQQ", "Europe/Berlin", 60),
    City(Coordinates(41.9028, 12.4964), "Rome", "IT", "ChIJvXRN6kUhLxMRxG0q6Oe12rs", "Europe/Rome", 60),
    City(Coordinates(40.4168, -3.7038), "Madrid", "ES", "ChIJgTwKgJcpQg0RaSKMYcHeNsQ", "Europe/Madrid", 60),
    City(Coordinates(50.8503, 4.3517), "Brussels", "BE", "ChIJ_58jrxu0w0cRMIB8qu4W6ws", "Europe/Brussels", 60),
    City(Coordinates(59.3293, 18.0686), "Stockholm", "SE", "ChIJywtkGTF2X0YRZnedZ9MnDag", "Europe/Stockholm", 60),
    City(Coordinates(47.4979, 19.0402), "Budapest", "HU", "ChIJyc_U0TT4oUcRgB-vz6vP3Kk", "Europe/Budapest", 60),
    City(Coordinates(53.3498, -6.2603), "Dublin", "IE", "ChIJL6wn6oAOZ0gRoHExl6nHAAE", "Europe/Dublin", 0),
    City(Coordinates(55.7558, 37.6173), "Moscow", "RU", "ChIJybDUc_xKtUYRTM9XV8zWRD0", "Europe/Moscow", 180),

    // Asia
    City(Coordinates(35.6895, 139.6917), "Tokyo", "JP", "ChIJ51cu8IcbXWARiRtXIothAS4", "Asia/Tokyo", 540),
    City(Coordinates(31.2304, 121.4737), "Shanghai", "CN", "ChIJMzz1sUBwsjURoWTDI5QSlQI", "Asia/Shanghai", 480),
    City(Coordinates(28.6139, 77.2090), "New Delhi", "IN", "ChIJLbZ-NFv9DDkRzk0gTkm3wlI", "Asia/Kolkata", 330),
    City(Coordinates(1.3521, 103.8198), "Singapore", "SG", "ChIJdZOLiiMR2jERxPWrUs9peIg", "Asia/Singapore", 480),
    City(Coordinates(25.276987, 55.296249), "Dubai", "AE", "ChIJRcbZaklDXz4RYlEphFBu5r0", "Asia/Dubai", 240),
    City(Coordinates(37.5665, 126.9780), "Seoul", "KR", "ChIJzWXFYYuifDUR64Pq5LTtioU", "Asia/Seoul", 540),
    City(Coordinates(39.9042, 116.4074), "Beijing", "CN", "ChIJuSwU55ZS8DURiqkPryBWYrk", "Asia/Shanghai", 480),
    City(Coordinates(13.7563, 100.5018), "Bangkok", "TH", "ChIJ82ENKDJgHTERIEjiXbIAAQE", "Asia/Bangkok", 420),
    City(Coordinates(41.0082, 28.9784), "Istanbul", "TR", "ChIJawhoAASf2xQRmU3uxBQD9sA", "Europe/Istanbul", 180),
    City(Coordinates(19.0760, 72.8777), "Mumbai", "IN", "ChIJwe1EZjDG5zsRaYxkjY_tpF0", "Asia/Kolkata", 330)
)
