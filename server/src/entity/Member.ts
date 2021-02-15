import { Entity, PrimaryGeneratedColumn, Column } from 'typeorm';

@Entity()
export class Member {
    @PrimaryGeneratedColumn()
    id!: number;

    @Column()
    organization!: number;

    @Column({ type: 'varchar', length: 50 })
    name!: string;

    @Column({ type: 'varchar', length: 500 })
    password!: string;

    @Column({ type: 'varchar', length: 300, nullable: true })
    image!: string;
}
