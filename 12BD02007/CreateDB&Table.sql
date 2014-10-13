create database [Lab3]
GO

USE [Lab3]
GO
/****** Object:  Table [dbo].[Student]    Script Date: 10/10/2014 20:00:56 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Student](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Name] [varchar](100) COLLATE SQL_Latin1_General_CP1251_CI_AS NOT NULL,
	[GPA] [decimal](4, 2) NULL,
 CONSTRAINT [PK_Student] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (IGNORE_DUP_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF