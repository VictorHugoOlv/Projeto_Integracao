#define MyAppName "ProjetoIntegração"
#define MyAppVersion "1.2"
#define MyAppPublisher "Victor Hugo"
#define MyAppURL "http://www.projetointegracao.com/"
#define MyAppExeName "ProjetoIntegracao-1.0-SNAPSHOT.exe"
#define SourcePath "..\..\..\..\src\main\deploy\package\windows"

[Setup]
AppId={{E7D60282-32DA-4559-B9CC-AE2655D000FD}}
AppName={#MyAppName}
AppVersion={#MyAppVersion}
AppPublisher={#MyAppPublisher}
AppPublisherURL={#MyAppURL}
AppSupportURL={#MyAppURL}
AppUpdatesURL={#MyAppURL}

DefaultDirName={pf}\{#MyAppName}
SetupIconFile={#SourcePath}\IntegrationProject-setup-icon.ico
DisableProgramGroupPage=yes
OutputDir=.
OutputBaseFilename=ProjetoIntegracao-Installer
Compression=lzma
SolidCompression=yes

[Languages]
Name: "english"; MessagesFile: "compiler:Default.isl"
Name: "brazilianportuguese"; MessagesFile: "compiler:Languages\BrazilianPortuguese.isl"

[Tasks]
Name: "desktopicon"; Description: "{cm:CreateDesktopIcon}"; GroupDescription: "{cm:AdditionalIcons}"; Flags: unchecked

[Files]
Source: "{#SourcePath}\IntegrationProject-setup-icon.ico"; DestDir: "{app}"; Flags: ignoreversion
Source: "{#SourcePath}\IntegrationProject.ico"; DestDir: "{app}"; Flags: ignoreversion
Source: "{#SourcePath}\..\..\..\..\targetjfx\native\ProjetoIntegracao-1.0-SNAPSHOT\{#MyAppExeName}"; DestDir: "{app}"; Flags: ignoreversion

[Icons]
Name: "{commonprograms}\{#MyAppName}"; Filename: "{app}\{#MyAppExeName}"
Name: "{commondesktop}\{#MyAppName}"; Filename: "{app}\{#MyAppExeName}"; Tasks: desktopicon

[Run]
Filename: "{app}\{#MyAppExeName}"; Description: "{cm:LaunchProgram,{#StringChange(MyAppName, '&', '&&')}}"; Flags: shellexec postinstall skipifsilent

