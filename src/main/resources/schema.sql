create unique index if not exists user_email_ui on users (email) where deleted = false;
