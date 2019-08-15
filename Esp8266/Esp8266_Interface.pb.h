/* Automatically generated nanopb header */
/* Generated by nanopb-0.4.0-dev */

#ifndef PB_ESP8266_ESP8266_INTERFACE_PB_H_INCLUDED
#define PB_ESP8266_ESP8266_INTERFACE_PB_H_INCLUDED
#include "pb.h"

/* @@protoc_insertion_point(includes) */
#if PB_PROTO_HEADER_VERSION != 40
#error Regenerate this file with the current version of nanopb generator.
#endif

#ifdef __cplusplus
extern "C" {
#endif

/* Enum definitions */
typedef enum _esp8266_Esp8266_Command_Esp8266_Direction {
    esp8266_Esp8266_Command_Esp8266_Direction_MOTOR_FORWARD = 0,
    esp8266_Esp8266_Command_Esp8266_Direction_MOTOR_BACK = 1,
    esp8266_Esp8266_Command_Esp8266_Direction_WHEELS_LEFT = 2,
    esp8266_Esp8266_Command_Esp8266_Direction_WHEELS_RIGHT = 3,
    esp8266_Esp8266_Command_Esp8266_Direction_KEEP_ALIVE = 4
} esp8266_Esp8266_Command_Esp8266_Direction;
#define _esp8266_Esp8266_Command_Esp8266_Direction_MIN esp8266_Esp8266_Command_Esp8266_Direction_MOTOR_FORWARD
#define _esp8266_Esp8266_Command_Esp8266_Direction_MAX esp8266_Esp8266_Command_Esp8266_Direction_KEEP_ALIVE
#define _esp8266_Esp8266_Command_Esp8266_Direction_ARRAYSIZE ((esp8266_Esp8266_Command_Esp8266_Direction)(esp8266_Esp8266_Command_Esp8266_Direction_KEEP_ALIVE+1))

typedef enum _esp8266_Esp8266_Metrics_Esp8266_Return_Status_Codes {
    esp8266_Esp8266_Metrics_Esp8266_Return_Status_Codes_MALFORMED_COMMAND = 0,
    esp8266_Esp8266_Metrics_Esp8266_Return_Status_Codes_COULD_NOT_EXECUTE_UNABLE_TO_SEND_TO_CAR = 1,
    esp8266_Esp8266_Metrics_Esp8266_Return_Status_Codes_COULD_NOT_EXECUTE_RUNTIME_ERROR = 2,
    esp8266_Esp8266_Metrics_Esp8266_Return_Status_Codes_OPERATION_OK = 3
} esp8266_Esp8266_Metrics_Esp8266_Return_Status_Codes;
#define _esp8266_Esp8266_Metrics_Esp8266_Return_Status_Codes_MIN esp8266_Esp8266_Metrics_Esp8266_Return_Status_Codes_MALFORMED_COMMAND
#define _esp8266_Esp8266_Metrics_Esp8266_Return_Status_Codes_MAX esp8266_Esp8266_Metrics_Esp8266_Return_Status_Codes_OPERATION_OK
#define _esp8266_Esp8266_Metrics_Esp8266_Return_Status_Codes_ARRAYSIZE ((esp8266_Esp8266_Metrics_Esp8266_Return_Status_Codes)(esp8266_Esp8266_Metrics_Esp8266_Return_Status_Codes_OPERATION_OK+1))

/* Struct definitions */
typedef struct _esp8266_Esp8266_Command {
    esp8266_Esp8266_Command_Esp8266_Direction direction;
/* @@protoc_insertion_point(struct:esp8266_Esp8266_Command) */
} esp8266_Esp8266_Command;


typedef struct _esp8266_Esp8266_Metrics {
    double hcsr04_calibrate;
    esp8266_Esp8266_Metrics_Esp8266_Return_Status_Codes returnCode;
/* @@protoc_insertion_point(struct:esp8266_Esp8266_Metrics) */
} esp8266_Esp8266_Metrics;


/* Initializer values for message structs */
#define esp8266_Esp8266_Command_init_default     {_esp8266_Esp8266_Command_Esp8266_Direction_MIN}
#define esp8266_Esp8266_Metrics_init_default     {0, _esp8266_Esp8266_Metrics_Esp8266_Return_Status_Codes_MIN}
#define esp8266_Esp8266_Command_init_zero        {_esp8266_Esp8266_Command_Esp8266_Direction_MIN}
#define esp8266_Esp8266_Metrics_init_zero        {0, _esp8266_Esp8266_Metrics_Esp8266_Return_Status_Codes_MIN}

/* Field tags (for use in manual encoding/decoding) */
#define esp8266_Esp8266_Command_direction_tag    1
#define esp8266_Esp8266_Metrics_hcsr04_calibrate_tag 1
#define esp8266_Esp8266_Metrics_returnCode_tag   2

/* Struct field encoding specification for nanopb */
#define esp8266_Esp8266_Command_FIELDLIST(X, a) \
X(a, STATIC, REQUIRED, UENUM, direction, 1)
#define esp8266_Esp8266_Command_CALLBACK NULL
#define esp8266_Esp8266_Command_DEFAULT NULL

#define esp8266_Esp8266_Metrics_FIELDLIST(X, a) \
X(a, STATIC, REQUIRED, DOUBLE, hcsr04_calibrate, 1) \
X(a, STATIC, REQUIRED, UENUM, returnCode, 2)
#define esp8266_Esp8266_Metrics_CALLBACK NULL
#define esp8266_Esp8266_Metrics_DEFAULT NULL

extern const pb_msgdesc_t esp8266_Esp8266_Command_msg;
extern const pb_msgdesc_t esp8266_Esp8266_Metrics_msg;

/* Defines for backwards compatibility with code written before nanopb-0.4.0 */
#define esp8266_Esp8266_Command_fields &esp8266_Esp8266_Command_msg
#define esp8266_Esp8266_Metrics_fields &esp8266_Esp8266_Metrics_msg

/* Maximum encoded size of messages (where known) */
#define esp8266_Esp8266_Command_size             2
#define esp8266_Esp8266_Metrics_size             11

#ifdef __cplusplus
} /* extern "C" */
#endif
/* @@protoc_insertion_point(eof) */

#endif