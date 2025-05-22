#!/bin/sh

# Argumentos
DB1_HOST="$1"
DB1_PORT="$2"
DB2_HOST="$3"
DB2_PORT="$4"
EUREKA_URL="$5"

echo "🔄 Esperando que PostgreSQL esté disponible en $DB1_HOST:$DB1_PORT..."
until nc -z "$DB1_HOST" "$DB1_PORT"; do
  echo "⏳ Esperando $DB1_HOST:$DB1_PORT..."
  sleep 2
done
echo "✅ Base de datos 1 disponible."

echo "🔄 Esperando que PostgreSQL esté disponible en $DB2_HOST:$DB2_PORT..."
until nc -z "$DB2_HOST" "$DB2_PORT"; do
  echo "⏳ Esperando $DB2_HOST:$DB2_PORT..."
  sleep 2
done
echo "✅ Base de datos 2 disponible."

echo "🔄 Esperando que Eureka esté disponible en $EUREKA_URL..."
until curl -s "$EUREKA_URL" | grep -q "<applications>"; do
  echo "⏳ Eureka aún no responde con aplicaciones... reintentando..."
  sleep 2
done
echo "✅ Eureka está disponible."

echo "🚀 Iniciando aplicación..."
exec java -jar app.jar
