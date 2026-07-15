export function formatData (dataString) {
        if (!dataString) return "--";

            try {
                const data = new Date(dataString);
                
                return new Intl.DateTimeFormat('pt-BR', {
                    hour: '2-digit',
                    minute: '2-digit',
                    hour12: false
                }).format(data);
            } catch (e) {
                return "--";
            }
    }