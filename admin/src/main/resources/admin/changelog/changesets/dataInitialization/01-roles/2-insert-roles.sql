-- liquibase formatted sql
-- changeset rashmita.subedi:1

-- preconditions onFail:CONTINUE onError:HALT
-- precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM roles
INSERT INTO roles (description, icon, name, navigation, parent_name, permission, position, ui_group_name,version)
VALUES ('Root', '', 'Root', 'NONE', 'ROOT', 'NONE', 0, 'NONE',0),
       ('Admin Users', 'GrUserAdmin', 'Admin Users', '/adminUser', 'Root', 'ADMIN_USER', 1, 'NONE',0),
       ('Create Admin User', '', 'Create Admin User', 'create', 'Admin Users', 'CREATE_ADMIN_USER', 1, 'Admin Users',0),
       ('Modify Admin User', 'msi-edit', 'Modify Admin User', 'edit', 'Admin Users', 'MODIFY_ADMIN_USER', 2,
        'Admin Users',0),
       ('View Admin User', 'msi-view', 'View Admin User', 'view', 'Admin Users', 'VIEW_ADMIN_USER', 3, 'Admin Users',0),
       ('Delete Admin User', '', 'Delete Admin User', 'delete', 'Admin Users', 'DELETE_ADMIN_USER', 4, 'Admin Users',0),
       ('Block Admin User', '', 'Block Admin User', 'block', 'Admin Users', 'BLOCK_ADMIN_USER', 5, 'Admin Users',0),
       ('UnBlock Admin User', '', 'UnBlock Admin User', 'unblock', 'Admin Users', 'UNBLOCK_ADMIN_USER', 6,
        'Admin Users',0),
       ('Reopen Admin User', '', 'Reopen Admin User', 'reopen', 'Admin Users', 'REOPEN_ADMIN_USER', 7, 'Admin Users',0),
       ('Unlock Admin User', '', 'Unlock Admin User', 'unlock', 'Admin Users', 'UNLOCK_ADMIN_USER', 8, 'Admin Users',0),
       ('Reset Password Admin User', '', 'Reset Password Admin User', 'reset', 'Admin Users',
        'RESET_PASSWORD_ADMIN_USER', 9, 'Admin Users',0),
       ('Resend Account Activation Link Admin User', '', 'Resend Account Activation Link Admin User', 'resend',
        'Admin Users', 'RESEND_ACCOUNT_ACTIVATION_LINK_ADMIN_USER', 10, 'Admin Users',0),
       ('Reset Two Factor Authentication Admin User', '', 'Reset Two Factor Authentication Admin User',
        'resetTwoFactorAuth', 'Admin Users', 'RESET_TWO_FACTOR_AUTHENTICATION_ADMIN_USER', 11, 'Admin Users',0),
       ('Customers', 'BsPeople', 'Customers', '/customers', 'Root', 'CUSTOMERS', 2, 'NONE',0),
       ('Services', 'GrServices', 'Services', '/services', 'Root', 'SERVICES', 3, 'NONE',0),
       ('News', 'FaRegNewspaper', 'News', '/news', 'Services', 'NEWS', 1, 'Services',0),
       ('Event', 'MdEventAvailable', 'Event', '/event', 'Services', 'EVENT', 2, 'Services',0),
       ('Health Service', 'BiHealth', 'Health Service', '/healthService', 'Services', 'HEALTH_SERVICE', 3, 'Services',0),
       ('Education', 'IoSchoolSharp', 'Education', '/education', 'Services', 'EDUCATION', 4, 'Services',0),
       ('Tourism Areas', 'TbMapPin2', 'Tourism Areas', '/tourismAreas', 'Services', 'TOURISM_AREAS', 5, 'Services',0),
       ('Police', 'GiPoliceCar', 'Police', '/police', 'Services', 'POLICE', 6, 'Services',0),
       ('Helpline', 'TbDeviceLandlinePhone', 'Helpline', '/helpLine', 'Services', 'HELPLINE', 7, 'Services',0),
       ('Financial Institution', 'BsBank', 'Financial Institution', '/finance', 'Services', 'FINANCIAL_INSTITUTION', 8, 'Services',0),
       ('Queries', 'BsChatLeftText', 'Queries', '/queries', 'Root', 'QUERIES', 4, 'NONE',0),
       ('Settings', 'IoSettingsOutline', 'Settings', '/setting', 'Root', 'SETTING', 7, 'NONE',0),
       ('Style Guide', 'MdDesignServices', 'Style Guide', '/styleGuide', 'Root', 'STYLE_GUIDE', 8, 'NONE',0),
       ('Email Templates', 'msi-email-settings', 'Email Templates', 'email', 'Settings', 'EMAIL_TEMPLATE', 1,
        'Settings',0),
       ('View Email Template', '', 'View Email Template', 'view', 'Email Templates', 'VIEW_EMAIL_TEMPLATE', 1,
        'Email Templates',0),
       ('Modify Email Template', '', 'Modify Email Template', 'edit', 'Email Templates', 'MODIFY_EMAIL_TEMPLATE', 2,
        'Email Templates',0),
       ('SMS Templates', 'msi-sms-settings', 'SMS Templates', 'sms', 'Settings', 'SMS_TEMPLATE', 2, 'Settings',0),
       ('View SMS Template', '', 'View SMS Template', 'view', 'SMS Templates', 'VIEW_SMS_TEMPLATE', 1, 'SMS Templates',0),
       ('Modify SMS Template', '', 'Modify SMS Template', 'edit', 'SMS Templates', 'MODIFY_SMS_TEMPLATE', 2,
        'SMS Templates',0),
       ('Reminders', 'msi-bell-ring', 'Reminders', 'reminders', 'Settings', 'REMINDERS', 3, 'Settings',0),
       ('View Reminder', '', 'View Reminder', 'view', 'Reminders', 'VIEW_REMINDER', 1, 'Reminders',0),
       ('Modify Reminder', '', 'Modify Reminder', 'edit', 'Reminders', 'MODIFY_REMINDER', 2, 'Reminders',0),
       ('Access Groups', 'msi-staff', 'Access Groups', 'group', 'Settings', 'GROUP', 4, 'Settings',0),
       ('Create Access Group', '', 'Create Access Group', 'create', 'Access Groups', 'CREATE_GROUP', 1,
        'Access Groups',0),
       ('View Access Group', '', 'View Access Group', 'view', 'Access Groups', 'VIEW_GROUP', 2, 'Access Groups',0),
       ('Modify Access Group', '', 'Modify Access Group', 'edit', 'Access Groups', 'MODIFY_GROUP', 3, 'Access Groups',0),
       ('Delete Access Group', '', 'Delete Access Group', 'delete', 'Access Groups', 'DELETE_GROUP', 4,
        'Access Groups',0),
       ('Password Policy', 'msi-umbrella', 'Password Policy', 'passwordPolicy', 'Settings', 'PASSWORD_POLICY', 5,
        'Settings',0),
       ('View Password Policy', '', 'View Password Policy', 'view', 'Password Policy', 'VIEW_PASSWORD_POLICY', 1,
        'Password Policy',0),
       ('Modify Password Policy', '', 'Modify Password Policy', 'edit', 'Password Policy', 'MODIFY_PASSWORD_POLICY', 2,
        'Password Policy',0),
       ('Admin Configuration', 'msi-administrative-tool', 'Admin Configuration', 'adminConfiguration', 'Settings',
        'ADMIN_CONFIGURATION', 6, 'Settings',0),
       ('View Admin Configuration', '', 'View Admin Configuration', 'view', 'Admin Configuration',
        'VIEW_ADMIN_CONFIGURATION', 1, 'Admin Configuration',0),
       ('Modify Admin Configuration', '', 'Modify Admin Configuration', 'edit', 'Admin Configuration',
        'MODIFY_ADMIN_CONFIGURATION', 2, 'Admin Configuration',0);


