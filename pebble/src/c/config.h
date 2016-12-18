#pragma once

#include <pebble.h>

#define TEST  false  // Use local mock data
#define DEBUG false

#define VERSION "4.6"

#define BUFFER_SIZE_IN        256
#define BUFFER_SIZE_OUT       64
#define INITIAL_DELAY         500
#define NETWORK_MAX_LENGTH    32
#define COMM_TIMEOUT_MS       2000
#define SYNC_TIMER_INTERVAL_S 30
#define TRANSITION_DURATION   200
#define MAX_WAKEUPS           8