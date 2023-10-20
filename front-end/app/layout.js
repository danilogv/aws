export default function RootLayout({children}) {
    return (
        <html lang="pt-br">
            <title>Lista de Funcionários</title>
            <meta charSet="utf-8" />
            <body>
                {children}
            </body>
        </html>
    );
}
